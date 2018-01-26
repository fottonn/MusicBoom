package ru.bugmakers.controller.web;

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
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.web.ArtistAuthenticationResponseWeb;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.dto.social.*;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.FbAuth;
import ru.bugmakers.entity.auth.OauthToken;
import ru.bugmakers.entity.auth.VkAuth;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.enums.Sex;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.User2UserDtoConverter;
import ru.bugmakers.service.UserService;
import ru.bugmakers.utils.DateTimeFormatters;
import ru.bugmakers.validator.FbAccessTokenValidator;
import ru.bugmakers.validator.FbUserIdValidator;
import ru.bugmakers.validator.VkAccessTokenValidator;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/authentication/webapi")
public class SocialAuthenticationWeb extends MbController implements DateTimeFormatters {

    private static final String REDIRECT_DOMAIN = "http://mboom.com"; //TODO
    private static final String VK_REDIRECT_PATH = "/authentication/webapi/callback/vk";
    private static final String FB_REDIRECT_PATH = "/authentication/webapi/callback/fb";
    private static final String VK_REDIRECT_URI = REDIRECT_DOMAIN + VK_REDIRECT_PATH;
    private static final String FB_REDIRECT_URI = REDIRECT_DOMAIN + FB_REDIRECT_PATH;
    private static final String VK_CLIENT_ID = "6320864"; //TODO
    private static final String FB_CLIENT_ID = "6320864"; //TODO
    private static final String VK_CLIENT_SECRET = "7G5TlMXg3Gb1cOUJ7Usz"; //TODO
    private static final String FB_CLIENT_SECRET = "7G5TlMXg3Gb1cOUJ7Usz"; //TODO
    private static final String HTTPS = "https";
    private static final String VK_OAUTH_HOST = "oauth.vk.com";
    private static final String FB_OAUTH_HOST = "www.facebook.com";
    private static final String VK_API_HOST = "api.vk.com";
    private static final String VK_API_VERSION = "5.69";
    private static final String FB_API_VERSION = "v2.11";
    private static final String SOCIAL_CSRF_TOKEN = "";
    private static final String FB_GRAF_HOST = "graph.facebook.com";

    private static final String VK_CODE_RQ =
            new HttpUrl.Builder()
                    .scheme(HTTPS)
                    .host(VK_OAUTH_HOST)
                    .addPathSegment("authorize")
                    //clientId в vk.com
                    .addQueryParameter("client_id", VK_CLIENT_ID)
                    //окно авторизации пользователя на vk.com в виде всплывающего окна
                    .addQueryParameter("display", "page")
                    //адрес, на который будет передан код
                    .addQueryParameter("redirect_uri", VK_REDIRECT_URI)
                    //запрос доступа к email
                    .addQueryParameter("scope", "email")
                    //тип ответа от vk.com
                    .addQueryParameter("response_type", "code")
                    //версия API vk.com
                    .addQueryParameter("v", VK_API_VERSION)
                    //этот параметр будет передан в ответе в неизменном виде
                    .addQueryParameter("state", SOCIAL_CSRF_TOKEN)
                    .toString();

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

    private static final String GOOGLE_CODE_RQ =
            new HttpUrl.Builder()
                    .toString();

    private RestTemplate restTemplate;
    private UserService userService;
    private VkAccessTokenValidator vkAccessTokenValidator;
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
    public void setVkAccessTokenValidator(VkAccessTokenValidator vkAccessTokenValidator) {
        this.vkAccessTokenValidator = vkAccessTokenValidator;
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

    @GetMapping(value = "vk")
    public ModelAndView vkWebAuthentication() {
        return new ModelAndView(redirectTo(VK_CODE_RQ));
    }

    @GetMapping(value = "fb")
    public ModelAndView fbWebAuthentication() {
        return new ModelAndView(redirectTo(FB_CODE_RQ));
    }

    @GetMapping(value = "google")
    public ModelAndView googleAuthenticationWeb() {
        return new ModelAndView(redirectTo(GOOGLE_CODE_RQ));
    }

    @GetMapping(value = "/callback/vk", params = {"code"})
    public ResponseEntity<MbResponseToWeb> vkCallbackCode(@RequestParam("code") String code) {

        try {
            if (StringUtils.isBlank(code)) {
                throw MbException.create(MbError.AUE01);
            }

            //Получаем oauth токен
            final URI vkGetTokenUrl =
                    new HttpUrl.Builder()
                            .scheme(HTTPS)
                            .host(VK_OAUTH_HOST)
                            .addPathSegment("access_token")
                            .addQueryParameter("client_id", VK_CLIENT_ID)
                            .addQueryParameter("client_secret", VK_CLIENT_SECRET)
                            .addQueryParameter("redirect_uri", VK_REDIRECT_URI)
                            .addQueryParameter("code", code)
                            .build().uri();
            final VkAccessTokenRs vkAccessTokenRs = restTemplate.getForObject(vkGetTokenUrl, VkAccessTokenRs.class);

            //Валидируем ответ на запрос токена
            vkAccessTokenValidator.validate(vkAccessTokenRs);

            User user = userService.findUserByVkSocialId(vkAccessTokenRs.getUserId());
            //Если пользователь впервые аутентифицируется через сервис VK
            if (user == null || !user.isRegistered()) {
                user = new User();
                VkAuth vkAuth = new VkAuth(
                        vkAccessTokenRs.getUserId(),
                        new OauthToken(vkAccessTokenRs.getAccessToken(), vkAccessTokenRs.getExpiresIn()));
                vkAuth.setUser(user);
                user.setVkAuth(vkAuth);
                //Записываем, полученный токен в БД
                user = userService.saveUser(user);

                //Получаем дополнительные сведения о пользователе
                //В БД записывать сразу смысла нет, т.к. на фронте пользователь может поменять
                final URI vkGetUserInfoUrl =
                        new HttpUrl.Builder()
                                .scheme(HTTPS)
                                .addPathSegment("method")
                                .addPathSegment("users.get")
                                .addQueryParameter("user_ids", vkAccessTokenRs.getUserId())
                                .addQueryParameter("fields", "bdate, city, country, sex")
                                .addQueryParameter("access_token", vkAccessTokenRs.getAccessToken())
                                .host(VK_API_HOST)
                                .addQueryParameter("v", VK_API_VERSION)
                                .build().uri();
                final VkUserInfoRs vkUserInfoRs = restTemplate.getForObject(vkGetUserInfoUrl, VkUserInfoRs.class);
                if (vkUserInfoRs != null) {
                    VkUserInfo userInfo = vkUserInfoRs.getVkUserInfo();
                    user.withName(userInfo.getFirstName())
                            .withSurName(userInfo.getLastName())
                            .withEmail(vkAccessTokenRs.getEmail())
                            .withCountry(userInfo.getVkCountryDTO() != null ? userInfo.getVkCountryDTO().getTitle() : null)
                            .withCity(userInfo.getVkCityDTO() != null ? userInfo.getVkCityDTO().getTitle() : null)
                            .withVkContact(String.format("http://vk.com/id%s", vkAccessTokenRs.getUserId()));
                    String vkSex = userInfo.getSex();
                    user.setSex(vkSex == null || vkSex.equals("0") ? null : vkSex.equals("1") ? Sex.FEMALE : Sex.MALE);
                    String bdate = userInfo.getBdate();
                    if (StringUtils.isNotBlank(bdate)) {
                        DateTimeFormatter formatter = null;
                        if (bdate.matches("\\d{1,2}\\.\\d{1,2}")) {
                            formatter = new DateTimeFormatterBuilder()
                                    .appendPattern("d.M")
                                    .parseDefaulting(ChronoField.YEAR, 1900L)
                                    .toFormatter();
                        } else if (bdate.matches("\\d{1,2}\\.\\d{1,2}.\\d{4}")) {
                            formatter = DATE_FORMATTER;
                        }
                        if (formatter != null) {
                            user.setBirthDay(LocalDate.parse(userInfo.getBdate(), formatter));
                        }
                    }
                }
            } else {
                user.getVkAuth().getOauthToken().setToken(vkAccessTokenRs.getAccessToken());
                user.getVkAuth().getOauthToken().setExpireTime(vkAccessTokenRs.getExpiresIn());
                user = userService.saveUser(user);
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
    @GetMapping(value = "/callback/vk", params = {"error", "error_description"})
    public ResponseEntity<MbResponseToWeb> vkCallbackError() {
        return ResponseEntity.ok(new MbResponseToWeb(MbException.create(MbError.AUE01), RsStatus.ERROR));
    }

    @GetMapping(value = "/callback/fb", params = {"code", "state"})
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
                FbAuth fbAuth = new FbAuth(
                        fbUserInfoRs.getId(),
                        new OauthToken(fbAccessTokenRs.getAccessToken(), fbAccessTokenRs.getExpiresIn()));
                fbAuth.setUser(user);
                user.setFbAuth(fbAuth);
                //Записываем, полученный токен в БД
                user = userService.saveUser(user);

                user
                        .withName(fbUserInfoRs.getFirstName())
                        .withSurName(fbUserInfoRs.getLastName())
                        .withEmail(fbUserInfoRs.getEmail());
            } else {
                user.getFbAuth().getOauthToken().setToken(fbAccessTokenRs.getAccessToken());
                user.getFbAuth().getOauthToken().setExpireTime(fbAccessTokenRs.getExpiresIn());
                user = userService.saveUser(user);
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
    @GetMapping(value = "/callback/fb", params = {"error", "error_description", "error_reason"})
    public ResponseEntity<MbResponseToWeb> fbCallbackError() {
        return ResponseEntity.ok(new MbResponseToWeb(MbException.create(MbError.AUE01), RsStatus.ERROR));
    }


    private String redirectTo(String url) {
        return "redirect:" + url;
    }

}
