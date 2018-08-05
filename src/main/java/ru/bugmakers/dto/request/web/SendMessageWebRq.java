package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 06.12.2017.
 */
public class SendMessageWebRq extends SessionDataRequest {
    private String message;
    private String subject;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
