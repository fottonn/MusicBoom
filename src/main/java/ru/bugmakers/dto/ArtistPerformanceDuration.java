package ru.bugmakers.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Ayrat on 11.12.2017.
 */
public class ArtistPerformanceDuration {
    private Artist artist;
    private Period period;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
