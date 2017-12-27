package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.CommonUser;
import ru.bugmakers.dto.Photos;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class GetArtistResponseMobile extends CommonResponseToMobile {
    public GetArtistResponseMobile(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
    private Photos photos;
    private CommonUser commonUser;
    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public CommonUser getCommonUser() {
        return commonUser;
    }

    public void setCommonUser(CommonUser commonUser) {
        this.commonUser = commonUser;
    }
}
