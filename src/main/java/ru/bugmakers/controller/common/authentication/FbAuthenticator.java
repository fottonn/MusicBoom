package ru.bugmakers.controller.common.authentication;

import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ivan
 */
public class FbAuthenticator implements Authenticator {
    @Override
    public UserDTO authenticate(String token, String id) {
        return null;
    }
}
