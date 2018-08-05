package ru.bugmakers.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ivan
 */
public class RegistrationRequest {

    private UserDTO user;
    private String provider;
    @JsonProperty(value = "social_id")
    private String socialId;
    private String token;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

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
