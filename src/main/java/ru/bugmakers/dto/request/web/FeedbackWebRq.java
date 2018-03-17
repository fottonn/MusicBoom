package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 13.12.2017.
 */
public class FeedbackWebRq extends SessionDataRequest {

    private String type;
    private String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
