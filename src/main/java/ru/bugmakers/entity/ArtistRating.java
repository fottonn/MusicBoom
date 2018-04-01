package ru.bugmakers.entity;

import javax.persistence.*;

import static ru.bugmakers.entity.EntityConstants.ARTIST_RATING_GEN;
import static ru.bugmakers.entity.EntityConstants.ARTIST_RATING_SEQ;

/**
 * Created by Ayrat on 20.11.2017.
 */
@Entity
@Table(name = "artist_rating")
@SequenceGenerator(name = ARTIST_RATING_GEN, sequenceName = ARTIST_RATING_SEQ, allocationSize = 1)
public class ArtistRating {

    @Id
    @GeneratedValue(generator = ARTIST_RATING_GEN, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "city_rating")
    private Double cityRatng;

    @Column(name = "country_rating")
    private Double countryRating;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
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

