package ru.bugmakers.entity;

import org.hibernate.annotations.Type;
import org.hibernate.type.LocalDateTimeType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

import static ru.bugmakers.entity.EntityConstants.LOCAL_DATE_TIME_TYPE;

/**
 * Created by Ayrat on 16.11.2017.
 */
@Entity
@Table(name = "active_event")
public class ActiveEvent {

    @Id
    @GeneratedValue
    private Long id;

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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
