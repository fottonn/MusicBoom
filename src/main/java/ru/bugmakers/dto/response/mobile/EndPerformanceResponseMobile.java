package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.response.MbResponse;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class EndPerformanceResponseMobile extends MbResponse {

    private String earnedMoney;

    public String getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(String earnedMoney) {
        this.earnedMoney = earnedMoney;
    }
}
