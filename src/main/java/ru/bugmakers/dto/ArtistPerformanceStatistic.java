package ru.bugmakers.dto;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistPerformanceStatistic {
    private String id;
    private String donated;
    private String cashOut;
    private String tokens;
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

    public String getCashOut() {
        return cashOut;
    }

    public void setCashOut(String cashOut) {
        this.cashOut = cashOut;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
