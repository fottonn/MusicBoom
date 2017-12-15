package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class FinanceManagementResponse extends CommonResponseToMobile implements ResponseToMobile{

    public FinanceManagementResponse(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
}
