package ru.bugmakers.validator;

import org.springframework.stereotype.Component;
import ru.bugmakers.dto.request.mobile.RegistrationArtistRequestMobile;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 09.01.2018.
 */
@Component
public class ArtistRegistrationMobileValidator implements MbValidator<RegistrationArtistRequestMobile> {

    @Override
    public void validate(RegistrationArtistRequestMobile target) throws MbException {
        if (target == null || target.getUser() == null) {
            throw MbException.create(MbError.RGE01);
        }
    }
}
