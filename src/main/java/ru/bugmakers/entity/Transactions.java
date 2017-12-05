package ru.bugmakers.entity;

import ru.bugmakers.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by Ayrat on 16.11.2017.
 */
@javax.persistence.Entity
@Table(name="transactions")
public class Transactions {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserLogin senderId;
    @ManyToOne

    @JoinColumn(name = "user_id")
    private UserLogin recipientId;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "number")
    private String number;

    @Column(name = "date")
    private GregorianCalendar date;

    @Column(name = "status")
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserLogin getSenderId() {
        return senderId;
    }

    public void setSenderId(UserLogin senderId) {
        this.senderId = senderId;
    }

    public UserLogin getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(UserLogin recipientId) {
        this.recipientId = recipientId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
