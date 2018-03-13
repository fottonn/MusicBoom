package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ivan
 */
public class ArtistEditWebRq extends SessionDataRequest{

    private UserDTO artist;

    public UserDTO getArtist() {
        return artist;
    }

    public void setArtist(UserDTO artist) {
        this.artist = artist;
    }
}
