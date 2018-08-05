package ru.bugmakers.dto;

/**
 * Created by Ayrat on 13.12.2017.
 */
public class TransactionApprove {

    private String transactionId;
    private String transactionStatus;
    private String message;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
