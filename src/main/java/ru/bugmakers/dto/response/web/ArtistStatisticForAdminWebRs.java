package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.response.MbResponse;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistStatisticForAdminWebRs extends MbResponse {

    private String id;
    private String donated;
    private String cashout;
    private String balance;
    private String showTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDonated() {
        return donated;
    }

    public void setDonated(String donated) {
        this.donated = donated;
    }

    public String getCashout() {
        return cashout;
    }

    public void setCashout(String cashout) {
        this.cashout = cashout;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
