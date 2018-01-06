package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class EndPerformanceResponseMobile extends MbResponseToMobile {

    private String earnedMoney;

    public EndPerformanceResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public EndPerformanceResponseMobile(RsStatus status) {
        super(status);
    }

    public String getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(String earnedMoney) {
        this.earnedMoney = earnedMoney;
    }
}
