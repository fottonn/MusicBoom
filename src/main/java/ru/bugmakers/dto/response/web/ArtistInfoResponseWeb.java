package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.Artist;
import ru.bugmakers.dto.Photos;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistInfoResponseWeb extends CommonResponseToWeb {
    public ArtistInfoResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }

    private Artist artist;

    private Photos photos;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
