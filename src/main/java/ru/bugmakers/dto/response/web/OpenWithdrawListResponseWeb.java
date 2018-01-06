package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.MBTransaction;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

import java.util.List;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class OpenWithdrawListResponseWeb extends MbResponseToWeb {

    private List<MBTransaction> mbTransaction;

    public OpenWithdrawListResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public OpenWithdrawListResponseWeb(RsStatus status) {
        super(status);
    }

    public List<MBTransaction> getMbTransaction() {
        return mbTransaction;
    }

    public void setMbTransaction(List<MBTransaction> mbTransaction) {
        this.mbTransaction = mbTransaction;
    }
}
