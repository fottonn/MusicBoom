package ru.bugmakers.sheduler.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bugmakers.localpers.entity.ActiveEvent;
import ru.bugmakers.localpers.service.ActiveEventService;
import ru.bugmakers.service.PerformanceService;

import java.util.List;

/**
 * Created by Ivan
 */
@Component
public class PerformanceEndTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceEndTask.class);

    private PerformanceService performanceService;
    private ActiveEventService activeEventService;

    @Autowired
    public void setPerformanceService(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @Autowired
    public void setActiveEventService(ActiveEventService activeEventService) {
        this.activeEventService = activeEventService;
    }

    @Override
    public void run() {
        LOGGER.debug("End task have run!");
        List<ActiveEvent> allOverdueEvents = activeEventService.findAllOverdueEvents();
        allOverdueEvents.forEach(overdueEvent -> {
            if (activeEventService.isEventOverdue(overdueEvent)) {
                performanceService.performanceEnd(overdueEvent);
            }
        });
    }
}
