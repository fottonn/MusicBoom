package ru.bugmakers.localpers.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static ru.bugmakers.entity.EntityConstants.LOCAL_DATE_TIME_TYPE;

/**
 * Created by Ayrat on 16.11.2017.
 */
@Entity
@Table(name = "active_event")
public class ActiveEvent {

    @Id
    private Long userId;

    @Column(name = "lng")
    private String lng;

    @Column(name = "lat")
    private String lat;

    @Column(name = "begin_time")
    @Type(type = LOCAL_DATE_TIME_TYPE)
    private LocalDateTime beginTime;

    @Column(name = "end_time")
    @Type(type = LOCAL_DATE_TIME_TYPE)
    private LocalDateTime endTime;

    public ActiveEvent() {
    }

    public ActiveEvent(Long userId, String lng, String lat) {
        this.userId = userId;
        this.lng = lng;
        this.lat = lat;
        this.beginTime = LocalDateTime.now();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
