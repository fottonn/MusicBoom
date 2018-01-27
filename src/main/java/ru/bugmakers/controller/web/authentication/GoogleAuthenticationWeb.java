package ru.bugmakers.controller.web.authentication;

import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.web.ArtistAuthenticationResponseWeb;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.dto.social.GoogleAccessTokenRs;
import ru.bugmakers.dto.social.GoogleUserInfoRs;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.GoogleAuth;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.User2UserDtoConverter;
import ru.bugmakers.service.UserService;
import ru.bugmakers.validator.GoogleAccessTokenValidator;
import ru.bugmakers.validator.GoogleUserIdValidator;

import java.net.URI;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/authentication/webapi/google")
public class GoogleAuthenticationWeb extends SocialAuthenticationWeb {

    private static final String GOOGLE_CODE_RQ =
            new HttpUrl.Builder()
                    .scheme(HTTPS)
                    .host(GOOGLE_OAUTH_HOST)
                    .addPathSegment("o")
                    .addPathSegment("oauth2")
                    .addPathSegment("v2")
                    .addPathSegment("auth")
                    //clientId в google.com
                    .addQueryParameter("client_id", GOOGLE_CLIENT_ID)
                    //адрес, на который будет передан код
                    .addQueryParameter("redirect_uri", GOOGLE_REDIRECT_URI)
                    //этот параметр будет передан в ответе в неизменном виде
                    .addQueryParameter("state", SOCIAL_CSRF_TOKEN)
                    //запрос доступа к email и основной информации профиля
                    .addQueryParameter("scope", "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile")
                    .toString();

    private RestTemplate restTemplate;
    private UserService userService;
    private GoogleAccessTokenValidator googleAccessTokenValidator;
    private GoogleUserIdValidator googleUserIdValidator;
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
    public void setGoogleAccessTokenValidator(GoogleAccessTokenValidator googleAccessTokenValidator) {
        this.googleAccessTokenValidator = googleAccessTokenValidator;
    }

    @Autowired
    public void setGoogleUserIdValidator(GoogleUserIdValidator googleUserIdValidator) {
        this.googleUserIdValidator = googleUserIdValidator;
    }

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }

    @GetMapping
    public ModelAndView googleAuthenticationWeb() {
        return new ModelAndView(redirectTo(GOOGLE_CODE_RQ));
    }

    @GetMapping(value = "/callback", params = {"code", "state"})
    public ResponseEntity<MbResponseToWeb> googleCallbackCode(@RequestParam("code") String code, @RequestParam("state") String state) {
        try {
            if (StringUtils.isBlank(code)) {
                throw MbException.create(MbError.AUE12);
            }

            //Получаем oauth токен
            final URI googleGetTokenUrl =
                    new HttpUrl.Builder()
                            .scheme(HTTPS)
                            .host(GOOGLE_API_HOST)
                            .addPathSegment("oauth2")
                            .addPathSegment("v4")
                            .addPathSegment("token")
                            .addQueryParameter("client_id", GOOGLE_CLIENT_ID)
                            .addQueryParameter("client_secret", GOOGLE_CLIENT_SECRET)
                            .addQueryParameter("redirect_uri", GOOGLE_REDIRECT_URI)
                            .addQueryParameter("code", code)
                            .addQueryParameter("grant_type", "authorization_code")
                            .build().uri();
            final GoogleAccessTokenRs googleAccessTokenRs = restTemplate.getForObject(googleGetTokenUrl, GoogleAccessTokenRs.class);

            //Валидируем ответ на запрос токена
            googleAccessTokenValidator.validate(googleAccessTokenRs);

            //Получаем дополнительные сведения о пользователе
            final URI googleGetUserInfoUrl =
                    new HttpUrl.Builder()
                            .scheme(HTTPS)
                            .host(GOOGLE_API_HOST)
                            .addPathSegment("oauth2")
                            .addPathSegment("v2")
                            .addPathSegment("userinfo")
                            .addQueryParameter("access_token", googleAccessTokenRs.getAccessToken())
                            .build().uri();
            final GoogleUserInfoRs googleUserInfoRs = restTemplate.getForObject(googleGetUserInfoUrl, GoogleUserInfoRs.class);

            //Валидируем ответ на запрос Id пользователя в facebook.com
            googleUserIdValidator.validate(googleUserInfoRs);

            User user = userService.findUserByGoogleSocialId(googleUserInfoRs.getId());

            //Если пользователь впервые аутентифицируется через сервис facebook.com
            if (user == null || !user.isRegistered()) {
                user = new User();
                GoogleAuth googleAuth = new GoogleAuth(googleUserInfoRs.getId());
                googleAuth.setUser(user);
                user.setGoogleAuth(googleAuth);
                //Записываем пользователя в БД
                user = userService.saveUser(user);

                user
                        .withName(googleUserInfoRs.getName())
                        .withSurName(googleUserInfoRs.getSurName())
                        .withEmail(googleUserInfoRs.getEmail());
            } else {
                //Устанавливаем SecurityContext
                UserPrincipal userPrincipal = new UserPrincipal(user);
                Authentication auth = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.clearContext();
                SecurityContextHolder.getContext().setAuthentication(auth);
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
    @GetMapping(value = "/callback", params = {"error"})
    public ResponseEntity<MbResponseToWeb> googleCallbackError() {
        return ResponseEntity.ok(new MbResponseToWeb(MbException.create(MbError.AUE01), RsStatus.ERROR));
    }
}
