package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class ValidatePerformanceResponse extends CommonResponseToMobile implements ResponseToMobile {
 private String currentEarnedMoney;

    public ValidatePerformanceResponse(Errors errors, String successMessage) {
        super(errors, successMessage);
    }

    public String getCurrentEarnedMoney() {
        return currentEarnedMoney;
    }

    public void setCurrentEarnedMoney(String currentEarnedMoney) {
        this.currentEarnedMoney = currentEarnedMoney;
    }
}
