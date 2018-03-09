package ru.bugmakers.dto.request.mobile;

import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 27.11.2017.
 */
public class ListenerProfileMobileRq extends SessionDataRequest {

    private String country;
    private String city;
    private String isAttached;
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
