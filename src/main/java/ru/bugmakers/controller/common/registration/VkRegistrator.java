package ru.bugmakers.controller.common.registration;

import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.UserType;

/**
 * Created by Ivan
 */
@Component
public class VkRegistrator implements Registrator {
    @Override
    public UserDTO register(UserType userType, UserDTO user) {
        return null;
    }
}
