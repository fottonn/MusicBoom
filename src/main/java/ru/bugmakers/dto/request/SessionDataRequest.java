package ru.bugmakers.dto.request;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 08.12.2017.
 */
public class SessionDataRequest {
    private String sessionId;
    private String id;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
