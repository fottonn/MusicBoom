package ru.bugmakers.dto.request.mobile;

import ru.bugmakers.dto.request.SessionDataRequest;

public class SocialBindMobileRq extends SessionDataRequest {

    private String provider;
    private String socialId;
    private String token;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
