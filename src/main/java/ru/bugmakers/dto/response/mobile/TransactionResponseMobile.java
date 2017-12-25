package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class TransactionResponseMobile extends CommonResponseToMobile {
    public TransactionResponseMobile(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
}
