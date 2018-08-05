package ru.bugmakers.dto.response;

import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ivan
 */
public class RegistrationResponse extends MbResponse{

    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
