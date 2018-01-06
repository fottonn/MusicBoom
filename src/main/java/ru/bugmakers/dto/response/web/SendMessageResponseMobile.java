package ru.bugmakers.dto.response.web;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class SendMessageResponseMobile extends MbResponseToWeb {

    public SendMessageResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public SendMessageResponseMobile(RsStatus status) {
        super(status);
    }
}
