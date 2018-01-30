package ru.bugmakers.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.social.GoogleUserInfoRs;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
public class GoogleUserIdValidator implements MbValidator<GoogleUserInfoRs> {
    @Override
    public void validate(GoogleUserInfoRs target) throws MbException {
        if (target == null || StringUtils.isBlank(target.getId())) {
            throw MbException.create(MbError.AUE14);
        }
    }
}
