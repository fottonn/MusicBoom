package ru.bugmakers.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.CommonUser;
import ru.bugmakers.dto.Photos;
import ru.bugmakers.dto.StatOfPerformance;

/**
 * Created by Ayrat on 21.11.2017.
 */

public class RegistrationArtistRequestMobile {
    @JsonProperty("User")
    private CommonUser commonUser;
    private Photos photos;

    public CommonUser getCommonUser() {
        return commonUser;
    }

    public void setCommonUser(CommonUser commonUser) {
        this.commonUser = commonUser;
    }
}
