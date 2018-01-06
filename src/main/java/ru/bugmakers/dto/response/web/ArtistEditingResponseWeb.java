package ru.bugmakers.dto.response.web;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistEditingResponseWeb extends MbResponseToWeb {
    public ArtistEditingResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistEditingResponseWeb(RsStatus status) {
        super(status);
    }
}
