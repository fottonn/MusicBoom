package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.ArtistsLocation;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

import java.util.List;

/**
 * Created by Ayrat on 13.12.2017.
 */
public class UpdateMapResponseMobile extends MbResponseToMobile {

    private List<ArtistsLocation> artistsLocations;

    public UpdateMapResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public UpdateMapResponseMobile(RsStatus status) {
        super(status);
    }

    public List<ArtistsLocation> getArtistsLocations() {
        return artistsLocations;
    }

    public void setArtistsLocations(List<ArtistsLocation> artistsLocations) {
        this.artistsLocations = artistsLocations;
    }
}
