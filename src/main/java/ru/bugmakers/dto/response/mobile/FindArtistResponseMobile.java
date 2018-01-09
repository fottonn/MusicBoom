package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.ArtistPerformanceDuration;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

import java.util.List;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class FindArtistResponseMobile extends MbResponseToMobile {

    List<ArtistPerformanceDuration> artistPerformanceDurations;

    public FindArtistResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public FindArtistResponseMobile(RsStatus status) {
        super(status);
    }

    public List<ArtistPerformanceDuration> getArtistPerformanceDurations() {
        return artistPerformanceDurations;
    }

    public void setArtistPerformanceDurations(List<ArtistPerformanceDuration> artistPerformanceDurations) {
        this.artistPerformanceDurations = artistPerformanceDurations;
    }
}
