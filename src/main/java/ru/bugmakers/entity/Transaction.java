package ru.bugmakers.entity;

import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;
import ru.bugmakers.utils.UuidGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Ayrat on 16.11.2017.
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    private String id;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "sender_payment_kind")
    @Enumerated(EnumType.STRING)
    private MoneyBearerKind senderMoneyBearerKind;

    @Column(name = "recipient_payment_kind")
    @Enumerated(EnumType.STRING)
    private MoneyBearerKind recipientMoneyBearerKind;

    @Column(name = "amount", scale = 2)
    private BigDecimal amount;

    @Column(name = "fee", scale = 2)
    private BigDecimal fee;

    @Column(name = "number")
    private String number;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Transaction() {
        this.id = UuidGenerator.timeBasedUuidGenerate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public MoneyBearerKind getSenderMoneyBearerKind() {
        return senderMoneyBearerKind;
    }

    public void setSenderMoneyBearerKind(MoneyBearerKind senderMoneyBearerKind) {
        this.senderMoneyBearerKind = senderMoneyBearerKind;
    }

    public MoneyBearerKind getRecipientMoneyBearerKind() {
        return recipientMoneyBearerKind;
    }

    public void setRecipientMoneyBearerKind(MoneyBearerKind recipientMoneyBearerKind) {
        this.recipientMoneyBearerKind = recipientMoneyBearerKind;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
