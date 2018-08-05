package ru.bugmakers.mappers.converters;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.Event;
import ru.bugmakers.localpers.entity.ActiveEvent;

/**
 * Created by Ivan
 */
@Component
public class ActiveEvent2EventConverter implements MbConverter<ActiveEvent, Event>{
    @Override
    public Event convert(ActiveEvent source) {
        Event event = new Event();
        event.setLat(source.getLat());
        event.setLng(source.getLng());
        event.setStartDate(source.getBeginTime());
        event.setEndDate(source.getEndTime());
        event.setUserId(source.getUserId());
        return event;
    }
}
