package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.MBTransaction;
import ru.bugmakers.errors.Errors;

import java.util.List;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class ClosedWithdrawListResponseWeb extends CommonResponseToWeb {

    public ClosedWithdrawListResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
    List<MBTransaction> mbTransactionList;

    public List<MBTransaction> getMbTransactionList() {
        return mbTransactionList;
    }

    public void setMbTransactionList(List<MBTransaction> mbTransactionList) {
        this.mbTransactionList = mbTransactionList;
    }
}
