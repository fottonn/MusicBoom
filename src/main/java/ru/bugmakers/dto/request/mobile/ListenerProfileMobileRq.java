package ru.bugmakers.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 27.11.2017.
 */
public class ListenerProfileMobileRq extends SessionDataRequest {

    private String country;

    private String city;

    @JsonProperty("is_attached")
    private String isAttached;

    private String email;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsAttached() {
        return isAttached;
    }

    public void setIsAttached(String isAttached) {
        this.isAttached = isAttached;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
