package ru.bugmakers.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class Photos implements Serializable {
    private String avatar;
    private List<String> phots;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getPhots() {
        return phots;
    }

    public void setPhots(List<String> phots) {
        this.phots = phots;
    }
}
