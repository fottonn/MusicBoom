package ru.bugmakers.entity;

/**
 * Created by Ivan
 */
public class StatOfPerformance {

    private String allPerformances;
    private String hoursOfMonth;
    private String moneyOfMonth;
    private String averagePerformanceTime;

    private StatOfPerformance() {
    }

    public static final StatOfPerformance STAT_OF_PERFORMANCE = new StatOfPerformance();

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
