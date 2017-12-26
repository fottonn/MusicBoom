package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.MBTransaction;
import ru.bugmakers.errors.Errors;

import java.util.List;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class OpenWithdrawListResponseWeb extends CommonResponseToWeb{
    public OpenWithdrawListResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
    private List<MBTransaction> mbTransaction;

    public List<MBTransaction> getMbTransaction() {
        return mbTransaction;
    }

    public void setMbTransaction(List<MBTransaction> mbTransaction) {
        this.mbTransaction = mbTransaction;
    }
}
