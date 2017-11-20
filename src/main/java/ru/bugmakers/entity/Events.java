package ru.bugmakers.entity;

import ru.bugmakers.enums.EventType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.GregorianCalendar;

/**
 * Created by Ayrat on 14.11.2017.
 */
@Entity
@Table(name="EVENTS")
public class Events {
    @Id
    @GeneratedValue
    private Long events_id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User userId;

    @Column(name="event_name")
    private String eventName;

    @Column(name="event_type")
    private EventType eventType;

    @Column(name="event_description")
    private String eventDescription;

    @Column(name="start_date")
    private GregorianCalendar startDate;

    @Column(name="end_date")
    private GregorianCalendar endDate;

    @Column(name="location")
    private String location;

    @Column(name="lng")
    private String lng;

    @Column(name="lat")
    private String lat;

    public Long getEvents_id() {
        return events_id;
    }

    public void setEvents_id(Long events_id) {
        this.events_id = events_id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
