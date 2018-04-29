package ru.bugmakers.entity;

import ru.bugmakers.enums.EventType;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

import static ru.bugmakers.entity.EntityConstants.EVENT_GEN;
import static ru.bugmakers.entity.EntityConstants.EVENT_SEQ;

/**
 * Created by Ayrat on 14.11.2017.
 */
@Entity
@Table(name = "event")
@SequenceGenerator(name = EVENT_GEN, sequenceName = EVENT_SEQ, allocationSize = 1)
public class Event {

    @Id
    @GeneratedValue(generator = EVENT_GEN, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * Длительность выступления в минутах
     */
    @Column(name = "event_duration")
    private Long eventDuration;

    @Column(name = "location")
    private String location;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "user_id")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventDuration() {
        return eventDuration;
    }

    @PreUpdate
    @PrePersist
    private void setEventDuration() {
        this.eventDuration = Duration.between(startDate, endDate).toMinutes();
    }
}
