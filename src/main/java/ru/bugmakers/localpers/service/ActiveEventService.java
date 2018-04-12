package ru.bugmakers.localpers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.bugmakers.localpers.entity.ActiveEvent;
import ru.bugmakers.localpers.repository.ActiveEventRepo;
import ru.bugmakers.utils.GeoLocation;

import java.util.List;

/**
 * Created by Ivan
 */
@Service
public class ActiveEventService {

    private ActiveEventRepo activeEventRepo;

    @Autowired
    public void setActiveEventRepo(ActiveEventRepo activeEventRepo) {
        this.activeEventRepo = activeEventRepo;
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
}
