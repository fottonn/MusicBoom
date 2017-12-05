package ru.bugmakers.entity;

import javax.persistence.*;
import java.util.GregorianCalendar;

/**
 * Created by Ayrat on 16.11.2017.
 */
@Entity
@Table(name = "active_events")
public class ActiveEvents {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "lng")
    private String lng;

    @Column(name = "lat")
    private String lat;

    @Column(name = "begin_time")
    private GregorianCalendar beginTime;

    @Column(name = "end_time")
    private GregorianCalendar endTime;

    public void setEndTime(GregorianCalendar endTime) {
        this.endTime = endTime;
    }

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

    public GregorianCalendar getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(GregorianCalendar beginTime) {
        this.beginTime = beginTime;
    }
}
