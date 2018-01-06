package ru.bugmakers.dto.response.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bugmakers.dto.common.ErrorDTO;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 25.12.2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MbResponseToWeb extends MbResponse {
    public MbResponseToWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public MbResponseToWeb(RsStatus status) {
        super(status);
    }
}
