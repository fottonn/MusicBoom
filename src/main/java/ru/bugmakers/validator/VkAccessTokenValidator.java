package ru.bugmakers.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.social.VkAccessTokenRs;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
public class VkAccessTokenValidator implements MbValidator<VkAccessTokenRs> {

    @Override
    public void validate(VkAccessTokenRs target) throws MbException {
        if (target == null || StringUtils.isBlank(target.getAccessToken())) {
            throw MbException.create(MbError.AUE02);
        } else if (StringUtils.isBlank(target.getUserId())) {
            throw MbException.create(MbError.AUE03);
        }
    }
}
