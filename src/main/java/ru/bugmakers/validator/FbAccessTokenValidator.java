package ru.bugmakers.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.social.FbAccessTokenRs;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
public class FbAccessTokenValidator implements MbValidator<FbAccessTokenRs> {

    @Override
    public void validate(FbAccessTokenRs target) throws MbException {
        if (target == null || StringUtils.isBlank(target.getAccessToken())) {
            throw MbException.create(MbError.AUE10);
        }
    }
}
