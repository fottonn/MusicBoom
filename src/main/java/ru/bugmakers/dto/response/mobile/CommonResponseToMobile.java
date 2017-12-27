package ru.bugmakers.dto.response.mobile;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 13.12.2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommonResponseToMobile implements ResponseToMobile {
    private Errors errors;
    private String successMessage;

    public CommonResponseToMobile(Errors errors, String successMessage) {
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
