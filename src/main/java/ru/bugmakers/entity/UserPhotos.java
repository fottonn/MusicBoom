package ru.bugmakers.entity;

import javax.persistence.*;

/**
 * Created by Ayrat on 20.11.2017.
 */
@Table(name = "user_photos")
@javax.persistence.Entity
public class UserPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "photo")
    private String photoPath;

    @Column(name = "is_avatar")
    private Boolean isAvatar;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Boolean getAvatar() {
        return isAvatar;
    }

    public void setAvatar(Boolean avatar) {
        isAvatar = avatar;
    }
}
