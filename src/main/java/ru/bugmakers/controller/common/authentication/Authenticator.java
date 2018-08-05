package ru.bugmakers.controller.common.authentication;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
public interface Authenticator {

    //common
    String HTTPS = "https";

    //vk
    String VK_API_HOST = "api.vk.com";
    String VK_API_VERSION = "5.69";

    //facebook
    String FB_GRAF_HOST = "graph.facebook.com";

    //google
    String GOOGLE_API_HOST = "www.googleapis.com";

    UserDTO authenticate(String token, String id) throws MbException;

    UserDTO authenticate(String token, String id, String phone) throws MbException;

}
