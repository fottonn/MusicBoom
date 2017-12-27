package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.request.SessionDataRequest;

import java.util.List;

/**
 * Created by Ayrat on 05.12.2017.
 */
public class PhotosUploadRequestWeb extends SessionDataRequest {
    List<String> photoId;

    public List<String> getPhotoId() {
        return photoId;
    }

    public void setPhotoId(List<String> photoId) {
        this.photoId = photoId;
    }
}
