package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.MbResponse;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistInfoResponseWeb extends MbResponse {

    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
