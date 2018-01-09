package ru.bugmakers.dto.request.web;

/**
 * Created by Ayrat on 06.12.2017.
 */
public class UserFeedbackRequestWeb {

    private String sessionId;
    private String reason;
    private String msg;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
