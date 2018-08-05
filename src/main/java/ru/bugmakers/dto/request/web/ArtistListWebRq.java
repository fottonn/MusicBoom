package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 06.12.2017.
 */
public class ArtistListWebRq extends SessionDataRequest {

    private String page;
    private String size;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
