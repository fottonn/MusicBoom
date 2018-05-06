package ru.bugmakers.utils;

import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bugmakers.dto.social.FbUserInfoRs;
import ru.bugmakers.dto.social.GoogleUserInfoRs;
import ru.bugmakers.dto.social.VkUserInfoRs;

import java.net.URI;

@Component
public class SocialIdChecker {

    //common
    private static final String HTTPS = "https";
    //vk
    private static final String VK_API_HOST = "api.vk.com";
    private static final String VK_API_VERSION = "5.69";
    //facebook
    private static final String FB_GRAF_HOST = "graph.facebook.com";
    //google
    private static final String GOOGLE_API_HOST = "www.googleapis.com";

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isValidVkId(String token, String id) {
        boolean isValid = true;
        final URI vkGetUserInfoUrl =
                new HttpUrl.Builder()
                        .scheme(HTTPS)
                        .host(VK_API_HOST)
                        .addPathSegment("method")
                        .addPathSegment("users.get")
                        .addQueryParameter("user_ids", id)
                        .addQueryParameter("access_token", token)
                        .addQueryParameter("v", VK_API_VERSION)
                        .build().uri();
        final VkUserInfoRs vkUserInfoRs = restTemplate.getForObject(vkGetUserInfoUrl, VkUserInfoRs.class);

        if (vkUserInfoRs == null || vkUserInfoRs.getVkUserInfo() == null || vkUserInfoRs.getVkUserInfo().getId() == null
                || !vkUserInfoRs.getVkUserInfo().getId().equals(id)) {
            isValid = false;
        }
        return isValid;
    }

    public boolean isValidGoogleId(String token, String id) {
        boolean isValid = true;

        final URI googleGetUserInfoUrl =
                new HttpUrl.Builder()
                        .scheme(HTTPS)
                        .host(GOOGLE_API_HOST)
                        .addPathSegment("oauth2")
                        .addPathSegment("v3")
                        .addPathSegment("tokeninfo")
                        .addQueryParameter("id_token", token)
                        .build().uri();
        final GoogleUserInfoRs googleUserInfoRs = restTemplate.getForObject(googleGetUserInfoUrl, GoogleUserInfoRs.class);
        if (googleUserInfoRs == null || googleUserInfoRs.getId() == null || !googleUserInfoRs.getId().equals(id)) {
            isValid = false;
        }
        return isValid;
    }

    public boolean isValidFbId(String token, String id) {
        boolean isValid = true;
        final URI fbGetUserInfoUrl =
                new HttpUrl.Builder()
                        .scheme(HTTPS)
                        .host(FB_GRAF_HOST)
                        .addPathSegment("me")
                        .addQueryParameter("access_token", token)
                        .addQueryParameter("fields", "id")
                        .build().uri();
        final FbUserInfoRs fbUserInfoRs = restTemplate.getForObject(fbGetUserInfoUrl, FbUserInfoRs.class);

        if (fbUserInfoRs == null || fbUserInfoRs.getId() == null || !fbUserInfoRs.getId().equals(id)) {
            isValid = false;
        }
        return isValid;
    }


}
