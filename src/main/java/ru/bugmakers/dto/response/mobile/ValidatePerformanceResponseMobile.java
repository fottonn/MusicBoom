package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.response.MbResponse;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class ValidatePerformanceResponseMobile extends MbResponse {

    private String currentEarnedMoney;

    public String getCurrentEarnedMoney() {
        return currentEarnedMoney;
    }

    public void setCurrentEarnedMoney(String currentEarnedMoney) {
        this.currentEarnedMoney = currentEarnedMoney;
    }
}
