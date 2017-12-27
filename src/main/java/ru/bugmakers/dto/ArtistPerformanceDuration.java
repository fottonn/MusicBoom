package ru.bugmakers.dto;


/**
 * Created by Ayrat on 11.12.2017.
 */
public class ArtistPerformanceDuration {
    private CommonUser commonUser;
    private Period period;

    public CommonUser getCommonUser() {
        return commonUser;
    }

    public void setCommonUser(CommonUser commonUser) {
        this.commonUser = commonUser;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
