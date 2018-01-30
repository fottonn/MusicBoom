package ru.bugmakers.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.social.FbUserInfoRs;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
public class FbUserIdValidator implements MbValidator<FbUserInfoRs> {
    @Override
    public void validate(FbUserInfoRs target) throws MbException {
        if (target == null || StringUtils.isBlank(target.getId())) {
            throw MbException.create(MbError.AUE11);
        }
    }
}
