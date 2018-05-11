package ru.bugmakers.localpers.service;

import org.cfg4j.provider.ConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.bugmakers.localpers.entity.ActiveEvent;
import ru.bugmakers.localpers.repository.ActiveEventRepo;
import ru.bugmakers.sheduler.tasks.PerformanceEndTask;
import ru.bugmakers.sheduler.triggers.PerformanceEndTrigger;
import ru.bugmakers.utils.GeoLocation;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

import static java.time.LocalDateTime.now;

/**
 * Created by Ivan
 */
@Service
public class ActiveEventService {

    private ScheduledFuture activeEventsSchedule;

    private ActiveEventRepo activeEventRepo;
    private ConfigurationProvider appConfigProvider;
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private PerformanceEndTask performanceEndTask;
    private PerformanceEndTrigger performanceEndTrigger;

    @Autowired
    public void setActiveEventRepo(ActiveEventRepo activeEventRepo) {
        this.activeEventRepo = activeEventRepo;
    }

    @Autowired
    @Qualifier("appConfigProvider")
    public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
        this.appConfigProvider = appConfigProvider;
    }

    @Autowired
    @Qualifier("eventsEndThreadPoolTaskScheduler")
    public void setThreadPoolTaskScheduler(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    @Autowired
    public void setPerformanceEndTask(PerformanceEndTask performanceEndTask) {
        this.performanceEndTask = performanceEndTask;
    }

    @Autowired
    public void setPerformanceEndTrigger(PerformanceEndTrigger performanceEndTrigger) {
        this.performanceEndTrigger = performanceEndTrigger;
    }

    @Transactional("jpaLocalTransactionManager")
    public ActiveEvent saveActiveEvent(ActiveEvent activeEvent) {
        Assert.notNull(activeEvent.getUserId(), "");
        return activeEventRepo.saveAndFlush(activeEvent);
    }

    public ActiveEvent getActiveEventByUserId(Long userId) {
        Assert.notNull(userId, "");
        return activeEventRepo.findById(userId).orElse(null);
    }

    /**
     * Все активные выступления в радиусе от текущей позиции
     *
     * @param curPosition текущая позиция
     * @param radius      радиус в метрах
     * @return активные выступления
     */
    public List<ActiveEvent> getActiveEventsInRadius(GeoLocation curPosition, double radius) {

        GeoLocation[] boundCoor = curPosition.boundingCoordinates(radius);
        Double latMin = boundCoor[0].getDegLat();
        Double lngMin = boundCoor[0].getDegLon();
        Double latMax = boundCoor[1].getDegLat();
        Double lngMax = boundCoor[1].getDegLon();

        return activeEventRepo.findByLngBetweenAndLatBetweenOrderByUserId(lngMin, lngMax, latMin, latMax);
    }

    /**
     * Все активные выступления во всём мире
     *
     * @return все активные выступления
     */
    public List<ActiveEvent> getAllActiveEvents() {
        return activeEventRepo.findAll();
    }

    /**
     * Удаление активного выступления
     *
     * @param activeEvent выступление
     */
    public void deleteActiveEvent(ActiveEvent activeEvent) {
        activeEventRepo.delete(activeEvent);
    }

    /**
     * Поиск всех просроченных активных выступлений
     *
     * @return список просроченных выступлений
     */
    public List<ActiveEvent> findAllOverdueEvents() {
        return activeEventRepo.findAllOverdueEvents(
                now().minusSeconds(appConfigProvider.getProperty("event.silence.period", Long.class)));
    }

    /**
     * Проверка выступления на просроченность
     *
     * @param activeEvent активное выступление
     * @return вердикт проверки: true|false
     */
    public boolean isEventOverdue(ActiveEvent activeEvent) {
        boolean isOverdue = false;
        Optional<ActiveEvent> event = activeEventRepo.findById(activeEvent.getUserId());
        if (event.isPresent() && event.get().getLastUpdate().compareTo(now().minusSeconds(
                appConfigProvider.getProperty("event.silence.period", Long.class))) < 0) {
            isOverdue = true;
        }
        return isOverdue;
    }

    /**
     * Проверка наличия активных выступлений
     *
     * @return ниличие активных выступлений
     */
    public boolean isExistEvents() {
        return activeEventRepo.count() != 0;
    }

    /**
     * Стартует выступление
     *
     * @param userId идентификатор выступающего пользователя
     * @param lng    долгота
     * @param lat    широта
     */
    public void startEvent(Long userId, Double lng, Double lat) {
        saveActiveEvent(new ActiveEvent(userId, lng, lat));
        if (activeEventsSchedule == null || activeEventsSchedule.isDone()) {
            activeEventsSchedule = threadPoolTaskScheduler.schedule(performanceEndTask, performanceEndTrigger);
        }
    }

    /**
     * Проверка на существование активного выступления
     *
     * @param userId идентификатор пользователя
     * @return признак активного выступления
     */
    public boolean isExistsEventByUserId(Long userId) {
        return activeEventRepo.existsById(userId);
    }
}
