package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.Artist;
import ru.bugmakers.dto.Photos;
import ru.bugmakers.dto.common.ErrorDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistInfoResponseWeb extends MbResponseToWeb {

    private Artist artist;

    private Photos photos;

    public ArtistInfoResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistInfoResponseWeb(RsStatus status) {
        super(status);
    }

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
