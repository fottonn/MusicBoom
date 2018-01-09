package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.MBTransaction;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

import java.util.List;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class ClosedWithdrawListResponseWeb extends MbResponseToWeb {

    List<MBTransaction> mbTransactionList;

    public ClosedWithdrawListResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ClosedWithdrawListResponseWeb(RsStatus status) {
        super(status);
    }

    public List<MBTransaction> getMbTransactionList() {
        return mbTransactionList;
    }

    public void setMbTransactionList(List<MBTransaction> mbTransactionList) {
        this.mbTransactionList = mbTransactionList;
    }
}
