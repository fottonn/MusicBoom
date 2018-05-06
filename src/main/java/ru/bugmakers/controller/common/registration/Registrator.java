package ru.bugmakers.controller.common.registration;

import org.springframework.transaction.annotation.Transactional;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
public interface Registrator {
    @Transactional
    UserDTO register(UserType userType, UserDTO userDto, String socialId, String token) throws MbException;
}
