package ru.bugmakers.dto.response.web;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class ArtistPhotosUploadResponseWeb extends MbResponseToWeb {
    public ArtistPhotosUploadResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistPhotosUploadResponseWeb(RsStatus status) {
        super(status);
    }
}
