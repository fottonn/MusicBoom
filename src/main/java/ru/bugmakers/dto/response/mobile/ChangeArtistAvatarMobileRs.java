package ru.bugmakers.dto.response.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.response.MbResponse;

public class ChangeArtistAvatarMobileRs extends MbResponse {

    @JsonProperty(value = "avatar")
    private String avatarUrl;

    public ChangeArtistAvatarMobileRs(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
