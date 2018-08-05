package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.MbResponse;

import java.util.List;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class FindArtistResponseMobile extends MbResponse {

    private List<UserDTO> artists;

    public List<UserDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<UserDTO> artists) {
        this.artists = artists;
    }
}
