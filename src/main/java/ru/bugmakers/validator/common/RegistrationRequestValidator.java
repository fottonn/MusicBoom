package ru.bugmakers.validator.common;

import org.springframework.stereotype.Component;
import ru.bugmakers.dto.request.RegistrationRequest;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.validator.MbValidator;

/**
 * Created by Ivan
 */
@Component
public class RegistrationRequestValidator implements MbValidator<RegistrationRequest>{
    @Override
    public void validate(RegistrationRequest target) throws MbException {

    }
}
