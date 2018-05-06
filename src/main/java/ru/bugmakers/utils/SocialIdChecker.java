package ru.bugmakers.utils;

import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import ru.bugmakers.dto.social.FbUserInfoRs;
import ru.bugmakers.dto.social.GoogleUserInfoRs;
import ru.bugmakers.dto.social.VkUserInfoRs;

import java.net.URI;

@Component
public class SocialIdChecker {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialIdChecker.class);

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

        try {
            final VkUserInfoRs vkUserInfoRs = restTemplate.getForObject(vkGetUserInfoUrl, VkUserInfoRs.class);
            Assert.notNull(vkUserInfoRs, "Response from vk.com server is null!");
            Assert.notNull(vkUserInfoRs.getVkUserInfo(), "Response from vk.com server is null!");
            if (!id.equals(vkUserInfoRs.getVkUserInfo().getId())) {
                isValid = false;
            }
        } catch (Exception e) {
            LOGGER.error("Failed request to vk.com server", e);
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

        try {
            final GoogleUserInfoRs googleUserInfoRs = restTemplate.getForObject(googleGetUserInfoUrl, GoogleUserInfoRs.class);
            Assert.notNull(googleUserInfoRs, "Response from google.com server is null!");
            if (!id.equals(googleUserInfoRs.getId())) {
                isValid = false;
            }
        } catch (Exception e) {
            LOGGER.error("Failed request to google.com server", e);
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
        try {
            final FbUserInfoRs fbUserInfoRs = restTemplate.getForObject(fbGetUserInfoUrl, FbUserInfoRs.class);
            Assert.notNull(fbUserInfoRs, "Response from facebook.com server is null!");
            if (!id.equals(fbUserInfoRs.getId())) {
                isValid = false;
            }
        } catch (Exception e) {
            LOGGER.error("Failed request to facebook.com server", e);
        }
        return isValid;
    }
}
