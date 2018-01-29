package ru.bugmakers.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.mobile.RegistrationArtistRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.SocialProvider;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.UAMToArtistRegistrationResponseConverter;
import ru.bugmakers.mappers.User2UserDtoConverter;
import ru.bugmakers.mappers.UserDtoToEntitiesConverter;
import ru.bugmakers.utils.SecurityContextUtils;

/**
 * Created by Ayrat on 26.12.2017.
 */
@Service
public class ArtistRegistrationService {
    private UserDtoToEntitiesConverter userDtoToEntitiesConverter;
    private UserService userService;
    private UAMToArtistRegistrationResponseConverter registrationResponseConverter;
    private User2UserDtoConverter user2UserDtoConverter;

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

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
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

    public UserDTO artistSocialRegister(final UserDTO userDtoRq, SocialProvider provider) throws MbException {

        //проверяем, авторизовывался ли пользователь в социальных сетях
        User user = userService.findUserById(Long.valueOf(userDtoRq.getId()));
        switch (provider) {
            case VK:
                if (user == null || user.getVkAuth() == null || StringUtils.isBlank(user.getVkAuth().getSocialId())) {
                    throw MbException.create(MbError.RGE05);
                }
                break;
            case FB:
                if (user == null || user.getFbAuth() == null || StringUtils.isBlank(user.getFbAuth().getSocialId())) {
                    throw MbException.create(MbError.RGE06);
                }
                break;
            case GOOGLE:
                if (user == null || user.getGoogleAuth() == null || StringUtils.isBlank(user.getGoogleAuth().getSocialId())) {
                    throw MbException.create(MbError.RGE07);
                }
                break;
        }

        //проверяем email пользователя на наличие в базе
        if (userService.isExistsByEmail(userDtoRq.getEmail()) || userService.isExistsByLogin(userDtoRq.getEmail())) {
            throw MbException.create(MbError.RGE02);
        }

        //TODO наполнить entity данными из userDtoRq

        user = userService.saveUser(user);
        //аутентифицируем пользователя
        SecurityContextUtils.setAuthentication(user);
        return user2UserDtoConverter.convert(user);

    }

}
