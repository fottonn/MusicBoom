package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class StartPerformanceResponseMobile extends MbResponseToMobile {

    public StartPerformanceResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public StartPerformanceResponseMobile(RsStatus status) {
        super(status);
    }
}
