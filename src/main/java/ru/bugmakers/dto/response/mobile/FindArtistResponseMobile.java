package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

import java.util.List;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class FindArtistResponseMobile extends MbResponseToMobile {

    private List<UserDTO> artists;

    public FindArtistResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public FindArtistResponseMobile(RsStatus status) {
        super(status);
    }

    public List<UserDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<UserDTO> artists) {
        this.artists = artists;
    }
}
