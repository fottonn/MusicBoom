package ru.bugmakers.entity;

import javax.persistence.*;

/**
 * Created by Ayrat on 20.11.2017.
 */
@Entity
@Table(name = "artist_rating")
public class ArtistRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city_rating")
    private String cityRatng;

    @Column(name = "country_rating")
    private String countryRating;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityRatng() {
        return cityRatng;
    }

    public void setCityRatng(String cityRatng) {
        this.cityRatng = cityRatng;
    }

    public String getCountryRating() {
        return countryRating;
    }

    public void setCountryRating(String countryRating) {
        this.countryRating = countryRating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

