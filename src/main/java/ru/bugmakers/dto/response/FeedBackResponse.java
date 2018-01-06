package ru.bugmakers.dto.response;

import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class FeedBackResponse extends MbResponseToMobile {

    public FeedBackResponse(MbException e, RsStatus status) {
        super(e, status);
    }

    public FeedBackResponse(RsStatus status) {
        super(status);
    }
}
