package ru.bugmakers.dto;

import java.io.Serializable;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class StatOfPerformance implements Serializable {
    private String allPerfromances;
    private String hoursOfMonth;
    private String moneyOfMonth;
    private String averagePerformanceTime;

public StatOfPerformance(String allPerfromances, String hoursOfMonth, String moneyOfMonth, String averagePerformanceTime) {
        this.allPerfromances = allPerfromances;
        this.hoursOfMonth = hoursOfMonth;
        this.moneyOfMonth = moneyOfMonth;
        this.averagePerformanceTime = averagePerformanceTime;
    }

    public String getAllPerfromances() {
        return allPerfromances;
    }

    public void setAllPerfromances(String allPerfromances) {
        this.allPerfromances = allPerfromances;
    }

    public String getHoursOfMonth() {
        return hoursOfMonth;
    }

    public void setHoursOfMonth(String hoursOfMonth) {
        this.hoursOfMonth = hoursOfMonth;
    }

    public String getMoneyOfMonth() {
        return moneyOfMonth;
    }

    public void setMoneyOfMonth(String moneyOfMonth) {
        this.moneyOfMonth = moneyOfMonth;
    }

    public String getAveragePerformanceTime() {
        return averagePerformanceTime;
    }

    public void setAveragePerformanceTime(String averagePerformanceTime) {
        this.averagePerformanceTime = averagePerformanceTime;
    }
}
