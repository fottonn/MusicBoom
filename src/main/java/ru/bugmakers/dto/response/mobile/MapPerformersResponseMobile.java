package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.ArtistsLocation;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

import java.util.List;

/**
 * Created by Ayrat on 13.12.2017.
 */
public class MapPerformersResponseMobile extends MbResponseToMobile {

    private List<ArtistsLocation> artists;

    public MapPerformersResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public MapPerformersResponseMobile(RsStatus status) {
        super(status);
    }

    public List<ArtistsLocation> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistsLocation> artists) {
        this.artists = artists;
    }
}
