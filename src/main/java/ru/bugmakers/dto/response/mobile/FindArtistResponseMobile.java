package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.ArtistPerformanceDuration;
import ru.bugmakers.errors.Errors;

import java.util.List;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class FindArtistResponseMobile extends CommonResponseToMobile {
    public FindArtistResponseMobile(Errors errors, String successMessage) {
        super(errors, successMessage);
    }

    List<ArtistPerformanceDuration> artistPerformanceDurations;

    public List<ArtistPerformanceDuration> getArtistPerformanceDurations() {
        return artistPerformanceDurations;
    }

    public void setArtistPerformanceDurations(List<ArtistPerformanceDuration> artistPerformanceDurations) {
        this.artistPerformanceDurations = artistPerformanceDurations;
    }
}
