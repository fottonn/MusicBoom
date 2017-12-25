package ru.bugmakers.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class Photos implements Serializable {
    private String avatar;
    private List<String> photos;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
