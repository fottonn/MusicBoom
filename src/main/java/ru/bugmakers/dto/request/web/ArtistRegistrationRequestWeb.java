package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ayrat on 05.12.2017.
 */
public class ArtistRegistrationRequestWeb {

    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
