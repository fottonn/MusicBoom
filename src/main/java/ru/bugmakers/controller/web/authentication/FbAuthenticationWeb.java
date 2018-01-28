package ru.bugmakers.controller.web.authentication;

import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.web.ArtistAuthenticationResponseWeb;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.dto.social.FbAccessTokenRs;
import ru.bugmakers.dto.social.FbUserInfoRs;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.FbAuth;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.User2UserDtoConverter;
import ru.bugmakers.service.UserService;
import ru.bugmakers.utils.SecurityContextUtils;
import ru.bugmakers.validator.FbAccessTokenValidator;
import ru.bugmakers.validator.FbUserIdValidator;

import java.net.URI;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/authentication/webapi/fb")
public class FbAuthenticationWeb extends SocialAuthenticationWeb {

    private static final String FB_CODE_RQ =
            new HttpUrl.Builder()
                    .scheme(HTTPS)
                    .host(FB_OAUTH_HOST)
                    .addPathSegment(FB_API_VERSION)
                    .addPathSegment("dialog")
                    .addPathSegment("oauth")
                    //clientId в facebook.com
                    .addQueryParameter("client_id", FB_CLIENT_ID)
                    //адрес, на который будет передан код
                    .addQueryParameter("redirect_uri", FB_REDIRECT_URI)
                    //этот параметр будет передан в ответе в неизменном виде
                    .addQueryParameter("state", SOCIAL_CSRF_TOKEN)
                    //запрос доступа к email
                    .addQueryParameter("scope", "email")
                    //тип ответа от facebook.com
                    .addQueryParameter("response_type", "code")
                    .toString();

    private RestTemplate restTemplate;
    private UserService userService;
    private FbAccessTokenValidator fbAccessTokenValidator;
    private FbUserIdValidator fbUserIdValidator;
    private User2UserDtoConverter user2UserDtoConverter;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFbAccessTokenValidator(FbAccessTokenValidator fbAccessTokenValidator) {
        this.fbAccessTokenValidator = fbAccessTokenValidator;
    }

    @Autowired
    public void setFbUserIdValidator(FbUserIdValidator fbUserIdValidator) {
        this.fbUserIdValidator = fbUserIdValidator;
    }

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }


    @GetMapping
    public ModelAndView fbWebAuthentication() {
        return new ModelAndView(redirectTo(FB_CODE_RQ));
    }

    @GetMapping(value = "/callback", params = {"code", "state"})
    public ResponseEntity<MbResponseToWeb> fbCallbackCode(@RequestParam("code") String code, @RequestParam("state") String state) {
        try {
            if (StringUtils.isBlank(code)) {
                throw MbException.create(MbError.AUE09);
            }

            //Получаем oauth токен
            final URI fbGetTokenUrl =
                    new HttpUrl.Builder()
                            .scheme(HTTPS)
                            .host(FB_GRAF_HOST)
                            .addPathSegment(FB_API_VERSION)
                            .addPathSegment("oauth")
                            .addPathSegment("access_token")
                            .addQueryParameter("client_id", FB_CLIENT_ID)
                            .addQueryParameter("client_secret", FB_CLIENT_SECRET)
                            .addQueryParameter("redirect_uri", FB_REDIRECT_URI)
                            .addQueryParameter("code", code)
                            .build().uri();
            final FbAccessTokenRs fbAccessTokenRs = restTemplate.getForObject(fbGetTokenUrl, FbAccessTokenRs.class);

            //Валидируем ответ на запрос токена
            fbAccessTokenValidator.validate(fbAccessTokenRs);

            //Получаем дополнительные сведения о пользователе
            final URI fbGetUserInfoUrl =
                    new HttpUrl.Builder()
                            .scheme(HTTPS)
                            .host(FB_GRAF_HOST)
                            .addPathSegment("me")
                            .addQueryParameter("access_token", fbAccessTokenRs.getAccessToken())
                            .addQueryParameter("fields", "id, first_name, last_name, email")
                            .build().uri();
            final FbUserInfoRs fbUserInfoRs = restTemplate.getForObject(fbGetUserInfoUrl, FbUserInfoRs.class);

            //Валидируем ответ на запрос Id пользователя в facebook.com
            fbUserIdValidator.validate(fbUserInfoRs);

            User user = userService.findUserByFbSocialId(fbUserInfoRs.getId());

            //Если пользователь впервые аутентифицируется через сервис facebook.com
            if (user == null || !user.isRegistered()) {
                user = new User();
                FbAuth fbAuth = new FbAuth(fbUserInfoRs.getId());
                fbAuth.setUser(user);
                user.setFbAuth(fbAuth);
                //Записываем пользователя в БД
                user = userService.saveUser(user);

                user
                        .withName(fbUserInfoRs.getFirstName())
                        .withSurName(fbUserInfoRs.getLastName())
                        .withEmail(fbUserInfoRs.getEmail());
            } else {
                //Устанавливаем SecurityContext
                SecurityContextUtils.setAuthentication(user);
            }

            //Маппим прикладной объект User на транспортный объект
            ArtistAuthenticationResponseWeb rs = new ArtistAuthenticationResponseWeb(RsStatus.SUCCESS);
            UserDTO userDto = user2UserDtoConverter.convert(user);
            rs.setUser(userDto);
            return ResponseEntity.ok(rs);
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToWeb(e, RsStatus.ERROR));
        } catch (Exception e) {
            MbException mbException = MbException.create(MbError.UNE01);
            mbException.setDescription(e.getMessage());
            return ResponseEntity.ok(new MbResponseToWeb(mbException, RsStatus.ERROR));
        }
    }

    //Для случая, если вместо кода пришла ошибка
    @GetMapping(value = "/callback", params = {"error", "error_description", "error_reason"})
    public ResponseEntity<MbResponseToWeb> fbCallbackError() {
        return ResponseEntity.ok(new MbResponseToWeb(MbException.create(MbError.AUE01), RsStatus.ERROR));
    }

}
