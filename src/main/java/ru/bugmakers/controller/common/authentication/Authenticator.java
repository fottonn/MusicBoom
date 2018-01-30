package ru.bugmakers.controller.common.authentication;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
public interface Authenticator {

    UserDTO authenticate(String token, String id) throws MbException;

}
