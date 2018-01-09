package ru.bugmakers.validator;

import org.springframework.stereotype.Component;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 09.01.2018.
 */
@Component
public class ArtistRegistrationMobileValidator implements MbValidator {
    @Override
    public void validate(Object target) throws MbException {
        if (target == null) {
            throw new MbException(MbError.RGE01);
        }
    }
}
