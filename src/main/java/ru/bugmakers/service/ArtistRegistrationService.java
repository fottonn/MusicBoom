package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.dto.request.mobile.RegistrationArtistRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.UAMToArtistRegistrationResponseConverter;
import ru.bugmakers.mappers.UserDtoToEntitiesConverter;

/**
 * Created by Ayrat on 26.12.2017.
 */
@Service
public class ArtistRegistrationService {
    private UserDtoToEntitiesConverter userDtoToEntitiesConverter;
    private UserService userService;
    private UAMToArtistRegistrationResponseConverter registrationResponseConverter;

    @Autowired
    public void setUserDtoToEntitiesConverter(UserDtoToEntitiesConverter userDtoToEntitiesConverter) {
        this.userDtoToEntitiesConverter = userDtoToEntitiesConverter;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRegistrationResponseConverter(UAMToArtistRegistrationResponseConverter registrationResponseConverter) {
        this.registrationResponseConverter = registrationResponseConverter;
    }

    public ArtistRegistrationResponse artistRegister(RegistrationArtistRequestMobile userRequest) throws MbException {
        ArtistRegistrationResponse artistRegistrationResponse;
        String email = userRequest.getUser().getEmail();
        User userByEmail = userService.findUserByEmail(email);
        if (userByEmail != null) {
            throw MbException.create(MbError.RGE02);
        } else {
            User convert = userDtoToEntitiesConverter.convert(userRequest);
            User resultUser = userService.saveUser(convert);
            if (resultUser != null) {
                artistRegistrationResponse = registrationResponseConverter.convert(userRequest.getUser());
                if (artistRegistrationResponse == null) {
                    throw MbException.create(MbError.RGE04);
                }
            } else {
                throw MbException.create(MbError.RGE03);
            }
            return artistRegistrationResponse;
        }
    }
}
