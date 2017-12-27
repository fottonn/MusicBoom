package ru.bugmakers.dto.response.web;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 26.12.2017.
 */
public class RestorePasswordResponseWeb extends CommonResponseToWeb {
    public RestorePasswordResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
}
