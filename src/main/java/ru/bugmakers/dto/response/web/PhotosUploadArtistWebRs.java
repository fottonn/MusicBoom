package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.response.MbResponse;

import java.util.List;

public class PhotosUploadArtistWebRs extends MbResponse {

    private List<String> photosURLs;

    public PhotosUploadArtistWebRs(List<String> photosURLs) {
        this.photosURLs = photosURLs;
    }

    public List<String> getPhotosURLs() {
        return photosURLs;
    }

    public void setPhotosURLs(List<String> photosURLs) {
        this.photosURLs = photosURLs;
    }
}
