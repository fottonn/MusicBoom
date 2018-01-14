package ru.bugmakers.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bugmakers.dto.common.ErrorDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

import java.io.Serializable;

/**
 * Created by Ivan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MbResponse implements Serializable {

    private RsStatus status;
    private String sessionId;
    private ErrorDTO error;

    public MbResponse(MbException e, RsStatus status) {
        this.error = e != null ? new ErrorDTO(e.getMessage()) : null;
        this.status = status;
    }

    public MbResponse(RsStatus status) {
        this(null, status);
    }

    public RsStatus getStatus() {
        return status;
    }

    public void setStatus(RsStatus status) {
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }
}
