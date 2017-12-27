package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class AboutAppResponseMobile extends CommonResponseToMobile{
    public AboutAppResponseMobile(Errors errors, String successMessage) {
        super(errors, successMessage);

    }
}
