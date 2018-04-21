package ru.bugmakers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ayrat on 13.12.2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistsLocation {

    @JsonProperty("artist_id")
    private String artistId;
    private String longitude;
    private String latitude;
    @JsonProperty("icon_url")
    private String iconUrl;

    public ArtistsLocation() {
    }

    public ArtistsLocation(String artistId, String longitude, String latitude, String iconUrl) {
        this.artistId = artistId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.iconUrl = iconUrl;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
