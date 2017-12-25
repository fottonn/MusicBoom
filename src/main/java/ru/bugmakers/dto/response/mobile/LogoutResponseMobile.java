package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class LogoutResponseMobile extends CommonResponseToMobile{
    public LogoutResponseMobile(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
}
