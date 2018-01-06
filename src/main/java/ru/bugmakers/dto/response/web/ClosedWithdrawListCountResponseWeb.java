package ru.bugmakers.dto.response.web;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class ClosedWithdrawListCountResponseWeb extends MbResponseToWeb {

    private String count;

    public ClosedWithdrawListCountResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ClosedWithdrawListCountResponseWeb(RsStatus status) {
        super(status);
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
