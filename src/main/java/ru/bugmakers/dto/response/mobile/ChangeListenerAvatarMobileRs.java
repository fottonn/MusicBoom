package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.response.MbResponse;

public class ChangeListenerAvatarMobileRs extends MbResponse {

    private String avatarUrl;

    public ChangeListenerAvatarMobileRs(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
