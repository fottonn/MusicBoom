package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.ArtistPerformanceDuration;
import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 06.12.2017.
 */
public class ArtistDeleteRequestWeb extends SessionDataRequest {
    private ArtistPerformanceDuration artistPerformanceDuration;

    public ArtistPerformanceDuration getArtistPerformanceDuration() {
        return artistPerformanceDuration;
    }

    public void setArtistPerformanceDuration(ArtistPerformanceDuration artistPerformanceDuration) {
        this.artistPerformanceDuration = artistPerformanceDuration;
    }
}
