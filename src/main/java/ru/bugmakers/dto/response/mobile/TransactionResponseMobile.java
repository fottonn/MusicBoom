package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class TransactionResponseMobile extends MbResponseToMobile {

    public TransactionResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public TransactionResponseMobile(RsStatus status) {
        super(status);
    }
}
