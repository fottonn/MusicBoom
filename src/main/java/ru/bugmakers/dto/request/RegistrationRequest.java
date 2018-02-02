package ru.bugmakers.dto.request;

import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ivan
 */
public class RegistrationRequest {

    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
