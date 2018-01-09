package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.common.ErrorDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
public class ArtistAuthenticationResponseWeb extends MbResponseToWeb {

    private UserDTO user;

    public ArtistAuthenticationResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistAuthenticationResponseWeb(RsStatus status) {
        super(status);
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
