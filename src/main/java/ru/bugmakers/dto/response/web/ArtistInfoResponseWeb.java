package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.CommonUser;
import ru.bugmakers.dto.Photos;
import ru.bugmakers.dto.common.ErrorDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistInfoResponseWeb extends MbResponseToWeb {

    private CommonUser commonUser;

    private Photos photos;

    public ArtistInfoResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistInfoResponseWeb(RsStatus status) {
        super(status);
    }

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
