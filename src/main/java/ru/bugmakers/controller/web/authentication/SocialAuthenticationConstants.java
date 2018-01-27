package ru.bugmakers.controller.web.authentication;

/**
 * Created by Ivan
 */
public interface SocialAuthenticationConstants {

    //common
    String HTTPS = "https";
    String REDIRECT_DOMAIN = "http://mboom.com"; //TODO
    String SOCIAL_CSRF_TOKEN = "";

    //vk
    String VK_REDIRECT_PATH = "/authentication/webapi/vk/callback";
    String VK_REDIRECT_URI = REDIRECT_DOMAIN + VK_REDIRECT_PATH;
    String VK_CLIENT_ID = "6320864"; //TODO
    String VK_CLIENT_SECRET = "7G5TlMXg3Gb1cOUJ7Usz"; //TODO
    String VK_OAUTH_HOST = "oauth.vk.com";
    String VK_API_HOST = "api.vk.com";
    String VK_API_VERSION = "5.69";

    //facebook
    String FB_REDIRECT_PATH = "/authentication/webapi/fb/callback";
    String FB_REDIRECT_URI = REDIRECT_DOMAIN + FB_REDIRECT_PATH;
    String FB_CLIENT_ID = "6320864"; //TODO
    String FB_CLIENT_SECRET = "7G5TlMXg3Gb1cOUJ7Usz"; //TODO
    String FB_OAUTH_HOST = "www.facebook.com";
    String FB_API_VERSION = "v2.11";
    String FB_GRAF_HOST = "graph.facebook.com";

    //google
    String GOOGLE_REDIRECT_PATH = "/authentication/webapi/google/callback";
    String GOOGLE_REDIRECT_URI = REDIRECT_DOMAIN + GOOGLE_REDIRECT_PATH;
    String GOOGLE_CLIENT_ID = "6320864"; //TODO
    String GOOGLE_CLIENT_SECRET = "7G5TlMXg3Gb1cOUJ7Usz"; //TODO
    String GOOGLE_OAUTH_HOST = "accounts.google.com";
    String GOOGLE_API_HOST = "www.googleapis.com";

}
