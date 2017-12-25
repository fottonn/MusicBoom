package ru.bugmakers.dto.request.mobile;

import ru.bugmakers.dto.Artist;

/**
 * Created by Ayrat on 21.11.2017.
 */
public class RegistrationAtistRequest {

    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
