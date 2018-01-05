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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.web.ArtistAuthenticationResponseWeb;
import ru.bugmakers.dto.response.web.ResponseToWeb;
import ru.bugmakers.dto.social.VkAccessTokenRs;
import ru.bugmakers.dto.social.VkUserInfo;
import ru.bugmakers.dto.social.VkUserInfoRs;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.OauthToken;
import ru.bugmakers.entity.auth.VkAuth;
import ru.bugmakers.enums.Role;
import ru.bugmakers.enums.Sex;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.service.UserService;

import java.net.URI;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Collections;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by Ayrat on 05.12.2017.
 */
//TODO это не точно, нужно узнать как это делается, используя ВК и прочие сети
@RestController
@RequestMapping("/webapi/authentication/")
public class ArtistAuthenticationWeb extends CommonController {

    private static final String VK_REDIRECT_URI = "http://mboom.com/webapi/authentication/callback/vk";
    private static final String VK_CLIENT_ID = "6320864"; //TODO
    private static final String VK_CLIENT_SECRET = "7G5TlMXg3Gb1cOUJ7Usz"; //TODO
    private static final String HTTPS = "https";
    private static final String VK_OAUTH_HOST = "oauth.vk.com";
    private static final String VK_API_HOST = "api.vk.com";
    private static final String VK_API_VERSION = "5.69";
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
                    .toString();

    private RestTemplate restTemplate;
    private UserService userService;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseToWeb> artistWebAuthentication(@RequestParam("id") String id,
                                                                 @RequestParam("hash_password") String passwordHash) {
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "vk")
    public ModelAndView artistWebAuthVk() {
        return new ModelAndView(redirectTo(VK_CODE_RQ));
    }

    @GetMapping(value = "/callback/vk", params = {"code"})
    public ResponseEntity<ResponseToWeb> vkCallbackCode(@RequestParam("code") String code) {

        if (StringUtils.isBlank(code)) {
            //TODO throw MBExeption()
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
        if (isBlank(vkAccessTokenRs.getAccessToken())
                || isBlank(vkAccessTokenRs.getUserId())
                || isNotBlank(vkAccessTokenRs.getError())) {
            //TODO throw MBException() authentication error
            return ResponseEntity.ok(null);
        } else {

            User user = userService.findUserByVkSocialId(vkAccessTokenRs.getUserId());
            //Если пользователь впервые аутентифицируется через сервис VK
            if (user == null) {

                user = new User();
                VkAuth vkAuth = new VkAuth(
                        vkAccessTokenRs.getUserId(),
                        new OauthToken(vkAccessTokenRs.getAccessToken(), vkAccessTokenRs.getExpiresIn()));
                vkAuth.setUser(user);
                user.setRoles(Collections.singletonList(Role.ARTIST));
                user.setUserType(UserType.ARTIST);
                user.setVkAuth(vkAuth);
                //Записываем, полученный токен в БД
                user = userService.saveUser(user);

                //Получаем дополнительные сведения о пользователе
                //В БД записывать сразу смысла нет, т.к. на фронте пользователь может поменять
                final URI vkGetUserInfoUrl =
                        new HttpUrl.Builder()
                                .scheme(HTTPS)
                                .host(VK_API_HOST)
                                .addPathSegment("method")
                                .addPathSegment("users.get")
                                .addQueryParameter("user_ids", vkAccessTokenRs.getUserId())
                                .addQueryParameter("fields", "bdate, city, country, sex")
                                .addQueryParameter("access_token", vkAccessTokenRs.getAccessToken())
                                .addQueryParameter("v", VK_API_VERSION)
                                .build().uri();
                final VkUserInfoRs vkUserInfoRs = restTemplate.getForObject(vkGetUserInfoUrl, VkUserInfoRs.class);
                VkUserInfo userInfo = vkUserInfoRs.getVkUserInfo();
                user.withName(userInfo.getFirstName())
                        .withSurName(userInfo.getLastName())
                        .withEmail(vkAccessTokenRs.getEmail())
                        .withCountry(userInfo.getVkCountryDTO() != null ? userInfo.getVkCountryDTO().getTitle() : null)
                        .withCity(userInfo.getVkCityDTO() != null ? userInfo.getVkCityDTO().getTitle() : null);
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
                        formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
                    }
                    if (formatter != null) {
                        user.setBirthDay(LocalDate.parse(userInfo.getBdate(), formatter));
                    }
                }
            }

            //Устанавливаем SecurityContext
            UserPrincipal userPrincipal = new UserPrincipal(user);
            Authentication auth = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
            SecurityContextHolder.clearContext();
            SecurityContextHolder.getContext().setAuthentication(auth);
            //Получаем ID сессии
            String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
            //Маппим прикладной объект User на транспортный объект
            ArtistAuthenticationResponseWeb rs = new ArtistAuthenticationResponseWeb(null, "success");
            //TODO реализовать маппер
            UserDTO userDto = new UserDTO()
                    .withName(user.getName())
                    .withSurname(user.getSurName())
                    .withEmail(user.getEmail())
                    .withCountry(user.getCountry())
                    .withCity(user.getCity())
                    .withSex(user.getSex() != null ? user.getSex().name() : null)
                    .withBirthday(user.getBirthDay() != null ? user.getBirthDay().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : null)
                    .withSessionId(sessionId);
            rs.setUser(userDto);
            return ResponseEntity.ok(rs);
        }

    }

    //Для случая, если вместо кода пришла ошибка
    @GetMapping(value = "/callback/vk", params = {"error", "error_description"})
    public ResponseEntity<ResponseToWeb> vkCallbackError(@RequestParam("error") String error,
                                                         @RequestParam("error_description") String description) {
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "facebook")
    public ResponseEntity<ResponseToWeb> artistWebAuthFacebook() {
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "google")
    public ResponseEntity<ResponseToWeb> artistWebAuthGoogle() {
        return ResponseEntity.ok(null);
    }

    private String redirectTo(String url) {
        return "redirect:" + url;
    }

}
