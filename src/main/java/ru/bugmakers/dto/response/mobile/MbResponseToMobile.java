package ru.bugmakers.dto.response.mobile;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 13.12.2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MbResponseToMobile extends MbResponse {

    public MbResponseToMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public MbResponseToMobile(RsStatus status) {
        super(status);
    }
}
