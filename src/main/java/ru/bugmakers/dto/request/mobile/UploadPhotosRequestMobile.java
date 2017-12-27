package ru.bugmakers.dto.request.mobile;

import ru.bugmakers.dto.request.SessionDataRequest;

import java.util.List;

/**
 * Created by Ayrat on 08.12.2017.
 */
public class UploadPhotosRequestMobile extends SessionDataRequest {

    private List<String> photos;

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
