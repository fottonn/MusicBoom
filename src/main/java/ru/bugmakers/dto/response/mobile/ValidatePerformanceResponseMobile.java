package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class ValidatePerformanceResponseMobile extends MbResponseToMobile {

    private String currentEarnedMoney;

    public ValidatePerformanceResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public ValidatePerformanceResponseMobile(RsStatus status) {
        super(status);
    }

    public String getCurrentEarnedMoney() {
        return currentEarnedMoney;
    }

    public void setCurrentEarnedMoney(String currentEarnedMoney) {
        this.currentEarnedMoney = currentEarnedMoney;
    }
}
