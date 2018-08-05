package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.ArtistsLocation;
import ru.bugmakers.dto.response.MbResponse;

import java.util.List;

/**
 * Created by Ayrat on 13.12.2017.
 */
public class MapPerformersResponseMobile extends MbResponse {

    private List<ArtistsLocation> artists;

    public List<ArtistsLocation> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistsLocation> artists) {
        this.artists = artists;
    }
}
