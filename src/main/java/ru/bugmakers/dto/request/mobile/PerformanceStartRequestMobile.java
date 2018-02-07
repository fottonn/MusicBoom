package ru.bugmakers.dto.request.mobile;

import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ivan
 */
public class PerformanceStartRequestMobile extends SessionDataRequest {

    private String longitude;
    private String latitude;

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
}
