package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.repository.EventRepo;
import ru.bugmakers.repository.TransactionRepo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

import static ru.bugmakers.utils.DecimalFormatters.HOURS_FORMATTER;
import static ru.bugmakers.utils.DecimalFormatters.MONEY_FORMATTER;

/**
 * Created by Ivan
 */
@Service
public class EventService {

    private static final BigDecimal SIXTY = new BigDecimal(60);
    private static final BigDecimal THIRTY = new BigDecimal(30);
    private EventRepo eventRepo;
    private TransactionRepo transactionRepo;

    @Autowired
    public void setEventRepo(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Autowired
    public void setTransactionRepo(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    /**
     * Количество всех выступлений
     *
     * @param userId идентификатор пользователя
     * @return количество всех выступлений
     */
    public String getAllEvents(Long userId) {
        return String.valueOf(eventRepo.countByUserId(userId));
    }

    /**
     * Среднее время выступлений в месяц за всё время в часах
     *
     * @param userId идентификатор пользователя
     * @return среднее время выступлений в месяц в часах
     */
    public String getHoursOfMonth(Long userId) {
        LocalDateTime firstStartDate = eventRepo.getFirstStartDate(userId);
        LocalDateTime lastEndDate = eventRepo.getLastEndDate(userId);

        if (firstStartDate != null && lastEndDate != null) {
            Duration duration = Duration.between(firstStartDate, lastEndDate);
            BigDecimal totalDays = new BigDecimal(duration.toDays());
            BigDecimal totalMonths = totalDays.divide(THIRTY, RoundingMode.HALF_UP);
            BigDecimal allEventDuration = new BigDecimal(eventRepo.getAllEventDuration(userId)).divide(SIXTY, RoundingMode.HALF_UP);
            BigDecimal hoursOfMonth = allEventDuration.divide(totalMonths, RoundingMode.HALF_UP);
            return HOURS_FORMATTER.format(hoursOfMonth);
        } else {
            return String.valueOf(0);
        }
    }

    /**
     * Среднее количество заработанных денег в месяц за всё время
     *
     * @param userId идентификатор пользователя
     * @return среднее количество заработанных денег в месяц
     */
    public String getMoneyOfMonth(Long userId) {
        LocalDateTime firstStartDate = eventRepo.getFirstStartDate(userId);
        LocalDateTime lastEndDate = eventRepo.getLastEndDate(userId);

        if (firstStartDate != null && lastEndDate != null) {
            Duration duration = Duration.between(firstStartDate, lastEndDate);
            BigDecimal totalDays = new BigDecimal(duration.toDays());
            BigDecimal totalMonths = totalDays.divide(THIRTY, RoundingMode.HALF_UP);
            BigDecimal allMoney = transactionRepo.getReceivedMoney(userId);
            BigDecimal moneyOfMonth = allMoney.divide(totalMonths, RoundingMode.DOWN);
            return MONEY_FORMATTER.format(moneyOfMonth);
        } else {
            return String.valueOf(0);
        }
    }

    /**
     * Среднее время выступлений за всё время
     *
     * @param userId идентификатор пользователя
     * @return среднее время выступлений в часах
     */
    public String getAverageEventTime(Long userId) {
        BigDecimal allEvents = new BigDecimal(eventRepo.countByUserId(userId));

        if (allEvents.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal allEventsDuration = new BigDecimal(eventRepo.getAllEventDuration(userId)).divide(SIXTY, RoundingMode.HALF_UP);
            BigDecimal averageEventTime = allEventsDuration.divide(allEvents, RoundingMode.HALF_UP);
            return HOURS_FORMATTER.format(averageEventTime);
        } else {
            return String.valueOf(0);
        }
    }

    /**
     * Общее время выступлений в часах
     *
     * @param userId идентификатор пользователя
     * @return суммарное время всех выступлений в часах в формате ###.#
     */
    public String getTotalEventsTime(Long userId) {
        if (userId == null) return null;
        BigDecimal totalTime = new BigDecimal(eventRepo.getAllEventDuration(userId)).divide(SIXTY, 1, RoundingMode.HALF_UP);
        return HOURS_FORMATTER.format(totalTime);
    }

    /**
     * Общее время выступлений в часах за период
     *
     * @param userId идентификатор пользователя
     * @param start  начало опериода
     * @param end    конец периода
     * @return время выступлений в часах за период
     */
    public String getPeriodEventsTime(Long userId, LocalDateTime start, LocalDateTime end) {
        if (userId == null) return null;
        if (start == null) start = eventRepo.getFirstStartDate(userId);
        if (end == null) end = eventRepo.getLastEndDate(userId);
        BigDecimal duration = new BigDecimal(eventRepo.getAllEventDurationForPeriod(userId, start, end));
        BigDecimal hoursDuration = duration.divide(SIXTY, 1, RoundingMode.HALF_UP);
        return HOURS_FORMATTER.format(hoursDuration);
    }
}
