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
    private Double cityRatng;

    @Column(name = "country_rating")
    private Double countryRating;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCityRatng() {
        return cityRatng;
    }

    public void setCityRatng(Double cityRatng) {
        this.cityRatng = cityRatng;
    }

    public Double getCountryRating() {
        return countryRating;
    }

    public void setCountryRating(Double countryRating) {
        this.countryRating = countryRating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

