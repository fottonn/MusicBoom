package ru.bugmakers.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ivan
 */
public class ListenerMainPageMobileRq extends SessionDataRequest {

    @JsonProperty("artist_alias")
    private String artistAlias;

    public String getArtistAlias() {
        return artistAlias;
    }

    public void setArtistAlias(String artistAlias) {
        this.artistAlias = artistAlias;
    }
}
