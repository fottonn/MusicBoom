package ru.bugmakers.dto;



/**
 * Created by Ayrat on 11.12.2017.
 */
public class Artist {
    private String artistId;
    private Boolean isBlock;

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public Boolean getBlock() {
        return isBlock;
    }

    public void setBlock(Boolean block) {
        isBlock = block;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    private Period period;
}
