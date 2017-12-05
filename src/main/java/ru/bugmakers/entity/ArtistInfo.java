package ru.bugmakers.entity;

import ru.bugmakers.enums.Genre;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

/**
 * Created by Ayrat on 20.11.2017.
 */
@Table(name = "artist_info")
@Entity
public class ArtistInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creativity")
    private String creativity;

    @Column(name = "instrument")
    private String instrument;

    @Column(name = "genre")
    private Genre genre;

    @Column(name = "is_ordered")
    private Boolean isOrdered;

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
}
