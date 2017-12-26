package ru.bugmakers.dto.response.web;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class ClosedWithdrawListCountResponseWeb extends CommonResponseToWeb {
    public ClosedWithdrawListCountResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
