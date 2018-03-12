package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.Period;
import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ayrat on 06.12.2017.
 */
public class ArtistStatisticWithPeriodWebRq {
    private UserDTO artist;
    private Period period;

    public UserDTO getArtist() {
        return artist;
    }

    public void setArtist(UserDTO artist) {
        this.artist = artist;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
