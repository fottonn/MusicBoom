package ru.bugmakers.controller.common.authentication;

import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ivan
 */
public interface Authenticator {

    UserDTO authenticate(String token, String id);

}
