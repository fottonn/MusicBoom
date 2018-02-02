package ru.bugmakers.controller.common.registration;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
public interface Registrator {
    UserDTO register(UserType userType, UserDTO userDto) throws MbException;
}
