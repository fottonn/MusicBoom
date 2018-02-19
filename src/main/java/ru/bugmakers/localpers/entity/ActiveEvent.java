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
    private Double lng;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "begin_time")
    @Type(type = LOCAL_DATE_TIME_TYPE)
    private LocalDateTime beginTime;

    @Column(name = "end_time")
    @Type(type = LOCAL_DATE_TIME_TYPE)
    private LocalDateTime endTime;

    public ActiveEvent() {
    }

    public ActiveEvent(Long userId, Double lng, Double lat) {
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
