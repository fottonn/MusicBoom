package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.entity.Event;
import ru.bugmakers.localpers.entity.ActiveEvent;
import ru.bugmakers.localpers.service.ActiveEventService;
import ru.bugmakers.mappers.converters.ActiveEvent2EventConverter;

import java.time.LocalDateTime;

/**
 * Created by Ivan
 */
@Service
public class PerformanceService {

    private ActiveEventService activeEventService;
    private EventService eventService;
    private ActiveEvent2EventConverter activeEvent2EventConverter;

    @Autowired
    public void setActiveEventService(ActiveEventService activeEventService) {
        this.activeEventService = activeEventService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setActiveEvent2EventConverter(ActiveEvent2EventConverter activeEvent2EventConverter) {
        this.activeEvent2EventConverter = activeEvent2EventConverter;
    }

    public Event performanceEnd(ActiveEvent activeEvent) {
        if (activeEvent == null) return null;
        activeEventService.deleteActiveEvent(activeEvent);
        activeEvent.setEndTime(LocalDateTime.now());
        Event event = activeEvent2EventConverter.convert(activeEvent);
        return eventService.saveEvent(event);
    }

}
