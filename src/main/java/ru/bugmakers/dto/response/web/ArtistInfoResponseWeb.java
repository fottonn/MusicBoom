package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.CommonUser;
import ru.bugmakers.dto.Photos;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistInfoResponseWeb extends CommonResponseToWeb {
    public ArtistInfoResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }

    private CommonUser commonUser;

    private Photos photos;

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
