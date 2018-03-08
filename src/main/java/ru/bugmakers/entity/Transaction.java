package ru.bugmakers.entity;

import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;

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
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @Column(name = "sender_payment_kind")
    @Enumerated(EnumType.STRING)
    private MoneyBearerKind senderMoneyBearerKind;

    @Column(name = "recipient_payment_kind")
    @Enumerated(EnumType.STRING)
    private MoneyBearerKind recipientMoneyBearerKind;

    @Column(name = "amount", scale = 2)
    private BigDecimal amount;

    @Column(name = "number")
    private String number;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
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
