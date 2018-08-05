package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 06.12.2017.
 */
public class ArtistBlockWebRq extends SessionDataRequest {

    private String artistId;
    private Boolean block;

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }
}
