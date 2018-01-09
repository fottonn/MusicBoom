package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.mobile.RegistrationArtistRequestMobile;
import org.springframework.stereotype.Service;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.CommonUserToEntitiesConverter;

/**
 * Created by Ayrat on 26.12.2017.
 */
@Service
public class ArtistRegistrationService {
    CommonUserToEntitiesConverter commonUserToEntitiesConverter;
    UserService userService;
    @Autowired
    public void setCommonUserToEntitiesConverter(CommonUserToEntitiesConverter commonUserToEntitiesConverter) {
        this.commonUserToEntitiesConverter = commonUserToEntitiesConverter;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ArtistRegistrationResponse artistRegister(RegistrationArtistRequestMobile userRequest) throws MbException {
        UserDTO user;
        String email = userRequest.getUserDTO().getEmail();
        User userByEmail = userService.findUserByEmail(email);
        if (userByEmail != null) {
            throw new MbException(MbError.RGE02);
        }else {
            user = commonUserToEntitiesConverter.convert(userRequest);
            return null;
        }
    }
}
