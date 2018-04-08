package ru.bugmakers.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

import static ru.bugmakers.entity.EntityConstants.PHOTOS_GEN;
import static ru.bugmakers.entity.EntityConstants.PHOTOS_SEQ;

/**
 * Created by Ivan
 */
@Entity
@Table(name = "photos")
@SequenceGenerator(name = PHOTOS_GEN, sequenceName = PHOTOS_SEQ, allocationSize = 1)
public class Photo {

    @Id
    @GeneratedValue(generator = PHOTOS_GEN, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "user_id")
    private Long userId;

    public Photo() {
    }

    public Photo(String title, Long userId) {
        this.title = title;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
