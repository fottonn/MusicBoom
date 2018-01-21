package ru.bugmakers.validator;

import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.AuthDTO;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by Ivan
 */
@Component
public class JsonLoginPasswordValidator implements MbValidator<AuthDTO> {
    @Override
    public void validate(AuthDTO target) throws MbException {
        if (target == null || isBlank(target.getLogin()) || isBlank(target.getPassword())) {
            throw MbException.create(MbError.AUE04);
        }
    }
}
