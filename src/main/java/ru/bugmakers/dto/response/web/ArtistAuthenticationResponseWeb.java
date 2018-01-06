package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ivan
 */
public class ArtistAuthenticationResponseWeb extends CommonResponseToWeb {

    private UserDTO user;

    public ArtistAuthenticationResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
