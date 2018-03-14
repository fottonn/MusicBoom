package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistInfoResponseWeb extends MbResponseToWeb {

    private UserDTO user;

    public ArtistInfoResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistInfoResponseWeb(RsStatus status) {
        super(status);
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
