package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class ValidatePerformanceResponseMobile extends CommonResponseToMobile {
 private String currentEarnedMoney;

    public ValidatePerformanceResponseMobile(Errors errors, String successMessage) {
        super(errors, successMessage);
    }

    public String getCurrentEarnedMoney() {
        return currentEarnedMoney;
    }

    public void setCurrentEarnedMoney(String currentEarnedMoney) {
        this.currentEarnedMoney = currentEarnedMoney;
    }
}
