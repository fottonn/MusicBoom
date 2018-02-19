package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class PerformanceStartResponseMobile extends MbResponseToMobile {

    public PerformanceStartResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public PerformanceStartResponseMobile(RsStatus status) {
        super(status);
    }
}
