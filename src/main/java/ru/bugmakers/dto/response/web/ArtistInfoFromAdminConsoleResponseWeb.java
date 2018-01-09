package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

import java.util.List;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistInfoFromAdminConsoleResponseWeb extends MbResponseToWeb {

    private List<UserDTO> artists;

    public ArtistInfoFromAdminConsoleResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistInfoFromAdminConsoleResponseWeb(RsStatus status) {
        super(status);
    }

    public List<UserDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<UserDTO> artists) {
        this.artists = artists;
    }
}
