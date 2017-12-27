package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.ArtistsLocation;
import ru.bugmakers.errors.Errors;

import java.util.List;

/**
 * Created by Ayrat on 13.12.2017.
 */
public class UpdateMapResponseMobile extends CommonResponseToMobile {
    private List<ArtistsLocation> artistsLocations;

    public UpdateMapResponseMobile(Errors errors, String successMessage) {
        super(errors, successMessage);
    }

    public List<ArtistsLocation> getArtistsLocations() {
        return artistsLocations;
    }

    public void setArtistsLocations(List<ArtistsLocation> artistsLocations) {
        this.artistsLocations = artistsLocations;
    }
}
