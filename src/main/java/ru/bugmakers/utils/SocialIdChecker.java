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
import ru.bugmakers.dto.social.VkAccessTokenRs;
import ru.bugmakers.dto.social.VkCheckTokenRs;

import java.net.URI;

@Component
public class SocialIdChecker {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialIdChecker.class);

    //common
    private static final String HTTPS = "https";
    //vk
    private static final String VK_API_HOST = "api.vk.com";
    private static final String VK_API_VERSION = "5.69";
    private static final String VK_CLIENT_ID = "6387565";
    private static final String VK_CLIENT_SECRET = "ppL7EUvRYz3AcAtc3Z45";
    private static final String VK_GRANT_TYPE = "client_credentials";

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

        final URI vkGetAccessTokenUrl =
                new HttpUrl.Builder()
                        .scheme(HTTPS)
                        .host(VK_API_HOST)
                        .addPathSegment("oauth")
                        .addPathSegment("access_token")
                        .addQueryParameter("v", VK_API_VERSION)
                        .addQueryParameter("client_id", VK_CLIENT_ID)
                        .addQueryParameter("client_secret", VK_CLIENT_SECRET)
                        .addQueryParameter("grant_type", VK_GRANT_TYPE)
                        .build().uri();


        try {

            final VkAccessTokenRs vkAccessTokenRs = restTemplate.getForObject(vkGetAccessTokenUrl, VkAccessTokenRs.class);
            Assert.notNull(vkAccessTokenRs, String.format("Response from %s is null!", vkGetAccessTokenUrl.toString()));
            Assert.notNull(vkAccessTokenRs.getAccessToken(), "Access token is null!");

            final URI vkGetUserInfoUrl =
                    new HttpUrl.Builder()
                            .scheme(HTTPS)
                            .host(VK_API_HOST)
                            .addPathSegment("method")
                            .addPathSegment("secure.checkToken")
                            .addQueryParameter("token", token)
                            .addQueryParameter("client_secret", VK_CLIENT_SECRET)
                            .addQueryParameter("access_token", vkAccessTokenRs.getAccessToken())
                            .addQueryParameter("v", VK_API_VERSION)
                            .build().uri();

            final VkCheckTokenRs vkCheckTokenRs = restTemplate.getForObject(vkGetUserInfoUrl, VkCheckTokenRs.class);
            Assert.notNull(vkCheckTokenRs, String.format("Response from %s is null!", vkGetAccessTokenUrl.toString()));
            Assert.isTrue("1".equals(vkCheckTokenRs.getSuccess()), String.format("Response from %s is not success!", vkGetUserInfoUrl.toString()));
            if (vkCheckTokenRs.getUserId() == null || !id.equals(vkCheckTokenRs.getUserId())) {
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
