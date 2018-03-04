package ru.bugmakers.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ivan
 */
public class GetArtistRqMobile extends SessionDataRequest {

    @JsonProperty("artist_id")
    private String artistId;

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}
