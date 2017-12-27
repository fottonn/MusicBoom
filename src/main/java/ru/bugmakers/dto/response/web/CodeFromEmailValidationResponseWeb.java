package ru.bugmakers.dto.response.web;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class CodeFromEmailValidationResponseWeb extends CommonResponseToWeb {
    public CodeFromEmailValidationResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
}
