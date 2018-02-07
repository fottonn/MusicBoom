package ru.bugmakers.localpers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.bugmakers.localpers.entity.ActiveEvent;
import ru.bugmakers.localpers.repository.ActiveEventRepo;

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
}
