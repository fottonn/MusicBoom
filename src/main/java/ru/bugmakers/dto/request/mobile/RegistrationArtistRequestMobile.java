package ru.bugmakers.dto.request.mobile;

import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ayrat on 21.11.2017.
 */

public class RegistrationArtistRequestMobile {

    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
