package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.Artist;
import ru.bugmakers.errors.Errors;

import java.util.List;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistInfoFromAdminConsoleResponseWeb extends CommonResponseToWeb {

    public ArtistInfoFromAdminConsoleResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
    private List<Artist> artists;

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
