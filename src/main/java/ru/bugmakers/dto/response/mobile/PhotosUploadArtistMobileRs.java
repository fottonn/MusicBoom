package ru.bugmakers.dto.response.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.response.MbResponse;

import java.util.List;

public class PhotosUploadArtistMobileRs extends MbResponse {
    private List<String> photosURLs;

    public PhotosUploadArtistMobileRs(List<String> photosURLs) {
        this.photosURLs = photosURLs;
    }

    public List<String> getPhotosURLs() {
        return photosURLs;
    }

    public void setPhotosURLs(List<String> photosURLs) {
        this.photosURLs = photosURLs;
    }
}
