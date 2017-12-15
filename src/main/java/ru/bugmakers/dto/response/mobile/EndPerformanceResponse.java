package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class EndPerformanceResponse extends CommonResponseToMobile implements ResponseToMobile  {
    private String earnedMoney;

    public EndPerformanceResponse(Errors errors, String successMessage) {
        super(errors, successMessage);
    }

    public String getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(String earnedMoney) {
        this.earnedMoney = earnedMoney;
    }
}
