package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.Artist;
import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 06.12.2017.
 */
public class ArtistDeleteRequest extends SessionDataRequest {
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
