package ru.bugmakers.dto.response.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 25.12.2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponseToWeb implements ResponseToWeb {
    private Errors errors;
    private String successMessage;

    public CommonResponseToWeb(Errors errors, String successMessage) {
        this.errors = errors;
        this.successMessage = successMessage;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
