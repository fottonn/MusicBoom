package ru.bugmakers.entity;

import javax.persistence.*;

/**
 * Created by Ayrat on 20.11.2017.
 */
@Table(name = "artist_raiting")
@javax.persistence.Entity
public class ArtistRaiting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city_raiting")
    private String cityRaitng;

    @Column(name = "country_raiting")
    private String countryRaiting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityRaitng() {
        return cityRaitng;
    }

    public void setCityRaitng(String cityRaitng) {
        this.cityRaitng = cityRaitng;
    }

    public String getCountryRaiting() {
        return countryRaiting;
    }

    public void setCountryRaiting(String countryRaiting) {
        this.countryRaiting = countryRaiting;
    }
}
