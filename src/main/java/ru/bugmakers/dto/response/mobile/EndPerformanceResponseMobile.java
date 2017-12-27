package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class EndPerformanceResponseMobile extends CommonResponseToMobile {
    private String earnedMoney;

    public EndPerformanceResponseMobile(Errors errors, String successMessage) {
        super(errors, successMessage);
    }

    public String getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(String earnedMoney) {
        this.earnedMoney = earnedMoney;
    }
}
