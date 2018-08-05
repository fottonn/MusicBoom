package ru.bugmakers.dto.request.mobile;

import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 27.11.2017.
 */

public class TransactionRequestMobile extends SessionDataRequest {

    private String recipientId;
    private String senderId;
    private String sum;
    private String numberOfTransaction;

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getNumberOfTransaction() {
        return numberOfTransaction;
    }

    public void setNumberOfTransaction(String numberOfTransaction) {
        this.numberOfTransaction = numberOfTransaction;
    }

}
