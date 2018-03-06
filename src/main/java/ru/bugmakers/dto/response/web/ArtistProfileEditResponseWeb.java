package ru.bugmakers.dto.response.web;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class ArtistProfileEditResponseWeb extends MbResponseToWeb {
    public ArtistProfileEditResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistProfileEditResponseWeb(RsStatus status) {
        super(status);
    }
}
