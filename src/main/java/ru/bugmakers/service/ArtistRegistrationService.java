package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.bugmakers.dto.request.mobile.RegistrationArtistRequestMobile;
import org.springframework.stereotype.Service;
import ru.bugmakers.dto.request.mobile.RegistrationAtistRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.entity.User;
import ru.bugmakers.mappers.CommonUserToEntitiesConverter;
import ru.bugmakers.validator.Validator;

/**
 * Created by Ayrat on 26.12.2017.
 */
@Service
public class ArtistRegistrationService {
    CommonUserToEntitiesConverter commonUserToEntitiesConverter;
    @Autowired
    public void setCommonUserToEntitiesConverter(CommonUserToEntitiesConverter commonUserToEntitiesConverter) {
        this.commonUserToEntitiesConverter = commonUserToEntitiesConverter;
    }

    public ArtistRegistrationResponse artistRegister(RegistrationArtistRequestMobile userRequest){
        User user;
        user = commonUserToEntitiesConverter.convert(userRequest);
        return null;
    }
}
