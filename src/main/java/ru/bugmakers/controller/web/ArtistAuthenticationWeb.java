package ru.bugmakers.controller.web;

import okhttp3.HttpUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.response.web.ResponseToWeb;

/**
 * Created by Ayrat on 05.12.2017.
 */
//TODO это не точно, нужно узнать как это делается, используя ВК и прочие сети
@RestController
@RequestMapping("/webapi/authentication/")
public class ArtistAuthenticationWeb extends CommonController {

    private static final String VK_REDIRECT_URI = "http://musicboom.ru/webapi/authentication/callback/vk";
    private static final String VK_AUTH =
            new HttpUrl.Builder()
                    .scheme("https")
                    .host("oauth.vk.com")
                    .addPathSegment("authorize")
                    //TODO наш clientId в vk.com
                    .addQueryParameter("client_id", "наш_id")
                    //окно авторизации пользователя на vk.com в виде всплывающего окна
                    .addQueryParameter("display", "popup")
                    //адрес, на который будет передан код
                    .addQueryParameter("redirect_uri", VK_REDIRECT_URI)
                    //запрос доступа к email
                    .addQueryParameter("scope", "email")
                    //тип ответа от vk.com
                    .addQueryParameter("response_type", "code")
                    //версия API vk.com
                    .addQueryParameter("v", "5.69")
                    .toString();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseToWeb> artistWebAuthentication(@RequestParam("id") String id,
                                                                 @RequestParam("hash_password") String passwordHash) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "vk")
    public String artistWebAuthVk() {
        return redirectTo(VK_AUTH);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/callback/vk", params = {"code"})
    public void vkCallbackCode(@RequestParam("code") String code) {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/callback/vk", params = {"error", "error_description"})
    public ResponseEntity<ResponseToWeb> vkCallbackError(@RequestParam("error") String error,
                                                         @RequestParam("error_description") String description) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "facebook")
    public ResponseEntity<ResponseToWeb> artistWebAuthFacebook() {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "google")
    public ResponseEntity<ResponseToWeb> artistWebAuthGoogle() {
        return ResponseEntity.ok(null);
    }

    private String redirectTo(String url) {
        return "redirect:" + url;
    }

}
