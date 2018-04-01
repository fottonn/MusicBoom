package ru.bugmakers.entity;

import ru.bugmakers.enums.Genre;

import javax.persistence.*;

import static ru.bugmakers.entity.EntityConstants.ARTIST_INFO_GEN;
import static ru.bugmakers.entity.EntityConstants.ARTIST_INFO_SEQ;

/**
 * Created by Ayrat on 20.11.2017.
 */
@Entity
@Table(name = "artist_info")
@SequenceGenerator(name = ARTIST_INFO_GEN, sequenceName = ARTIST_INFO_SEQ, allocationSize = 1)
public class ArtistInfo {

    @Id
    @GeneratedValue(generator = ARTIST_INFO_GEN, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "creativity")
    private String creativity;

    @Column(name = "instrument")
    private String instrument;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "is_ordered")
    private Boolean isOrdered;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreativity() {
        return creativity;
    }

    public void setCreativity(String creativity) {
        this.creativity = creativity;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Boolean getOrdered() {
        return isOrdered;
    }

    public void setOrdered(Boolean ordered) {
        isOrdered = ordered;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

