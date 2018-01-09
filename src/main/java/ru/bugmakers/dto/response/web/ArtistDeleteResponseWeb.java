package ru.bugmakers.dto.response.web;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistDeleteResponseWeb extends MbResponseToWeb {
    public ArtistDeleteResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistDeleteResponseWeb(RsStatus status) {
        super(status);
    }
}
