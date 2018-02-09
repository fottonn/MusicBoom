package ru.bugmakers.entity;

import org.hibernate.annotations.Type;
import ru.bugmakers.enums.EventType;

import javax.persistence.*;
import java.time.LocalDateTime;

import static ru.bugmakers.entity.EntityConstants.LOCAL_DATE_TIME_TYPE;

/**
 * Created by Ayrat on 14.11.2017.
 */
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "start_date")
    @Type(type = LOCAL_DATE_TIME_TYPE)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @Type(type = LOCAL_DATE_TIME_TYPE)
    private LocalDateTime endDate;

    @Column(name = "location")
    private String location;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "lat")
    private Double lat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
