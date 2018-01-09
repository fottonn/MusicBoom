package ru.bugmakers.dto.response.web;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class ArtistPhotosDeleteResponseWeb extends MbResponseToWeb {
    public ArtistPhotosDeleteResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistPhotosDeleteResponseWeb(RsStatus status) {
        super(status);
    }
}
