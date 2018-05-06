package ru.bugmakers.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bugmakers.dto.common.ErrorDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

import java.io.Serializable;

/**
 * Created by Ivan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MbResponse implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MbResponse.class);

    private RsStatus status = RsStatus.SUCCESS;
    private ErrorDTO error;

    public MbResponse() {
    }

    private MbResponse(MbException e, RsStatus status) {
        this.error = new ErrorDTO(e.getMessage());
        this.status = status;
    }

    private MbResponse(RsStatus status) {
        this.status = status;
        if (RsStatus.ERROR == status) {
            this.error = new ErrorDTO(MbException.create(MbError.UNE01).getMessage());
        }
    }

    private MbResponse(Exception e) {
        this.status = RsStatus.ERROR;
        if (e instanceof MbException) {
            this.error = new ErrorDTO(e.getMessage());
        } else {
            LOGGER.error(e.getMessage(), e);
            this.error = new ErrorDTO(MbException.create(MbError.UNE01).getMessage());
        }
    }

    public static MbResponse success() {
        return new MbResponse(RsStatus.SUCCESS);
    }

    public static MbResponse error(Exception e) {
        return new MbResponse(e);
    }

    public static MbResponse unauth(MbException e) {
        return new MbResponse(e, RsStatus.UNAUTHENTICATED);
    }

    public static MbResponse unreg(MbException e) {
        return new MbResponse(e, RsStatus.UNREGISTERED);
    }

    public RsStatus getStatus() {
        return status;
    }

    public void setStatus(RsStatus status) {
        this.status = status;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }
}
