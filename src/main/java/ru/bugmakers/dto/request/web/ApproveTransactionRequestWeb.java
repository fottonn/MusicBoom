package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.TransactionApprove;
import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 08.12.2017.
 */
public class ApproveTransactionRequestWeb extends SessionDataRequest {
    private TransactionApprove transactionRequest;

    public TransactionApprove getTransactionRequest() {
        return transactionRequest;
    }

    public void setTransactionRequest(TransactionApprove transactionRequest) {
        this.transactionRequest = transactionRequest;
    }
}
