package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class LogoutResponseMobile extends MbResponseToMobile {

    public LogoutResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public LogoutResponseMobile(RsStatus status) {
        super(status);
    }
}
