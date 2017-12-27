package ru.bugmakers.dto.response;

import ru.bugmakers.dto.response.mobile.CommonResponseToMobile;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class FeedBackResponse extends CommonResponseToMobile implements ResponseToMobile {
    public FeedBackResponse(Errors errors, String successMessage) {
        super(errors, successMessage);
    }

}
