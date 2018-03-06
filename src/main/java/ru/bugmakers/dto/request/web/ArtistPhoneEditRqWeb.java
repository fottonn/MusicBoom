package ru.bugmakers.dto.request.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ivan
 */
public class ArtistPhoneEditRqWeb extends SessionDataRequest {

    @JsonProperty("phone_number")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
