package ru.bugmakers.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by Ivan
 */
@JsonRootName("statOfPerformance")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatOfPerformanceDTO {

    private String allPerformances;
    private String hoursOfMonth;
    private String moneyOfMonth;
    private String averagePerformanceTime;

    public StatOfPerformanceDTO(String allPerformances, String hoursOfMonth, String moneyOfMonth, String averagePerformanceTime) {
        this.allPerformances = allPerformances;
        this.hoursOfMonth = hoursOfMonth;
        this.moneyOfMonth = moneyOfMonth;
        this.averagePerformanceTime = averagePerformanceTime;
    }

    public String getAllPerformances() {
        return allPerformances;
    }

    public void setAllPerformances(String allPerformances) {
        this.allPerformances = allPerformances;
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
