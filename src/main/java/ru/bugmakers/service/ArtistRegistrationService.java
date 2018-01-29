package ru.bugmakers.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.Role;
import ru.bugmakers.enums.SocialProvider;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.User2UserDtoConverter;
import ru.bugmakers.mappers.UserDtoToUserRegisterConverter;
import ru.bugmakers.utils.SecurityContextUtils;

/**
 * Created by Ayrat on 26.12.2017.
 */
@Service
public class ArtistRegistrationService {
    private UserDtoToUserRegisterConverter userDtoToUserRegisterConverter;
    private UserService userService;
    private User2UserDtoConverter user2UserDtoConverter;

    @Autowired
    public void setUserDtoToUserRegisterConverter(UserDtoToUserRegisterConverter userDtoToUserRegisterConverter) {
        this.userDtoToUserRegisterConverter = userDtoToUserRegisterConverter;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }

    public UserDTO artistRegister(UserDTO userDtoRq) throws MbException {
        if (userDtoRq == null) return null;
        UserDTO userDtoRs;
        String email = userDtoRq.getEmail();
        if (userService.isExistsByEmail(email)) {
            throw MbException.create(MbError.RGE02);
        } else {
            User convert = userDtoToUserRegisterConverter.convert(userDtoRq);
            convert.setUserType(UserType.ARTIST);
            convert.setRoles(Role.ARTIST);
            convert.setRegistered(true);
            User user = userService.saveUser(convert);
            userDtoRs = user2UserDtoConverter.convert(user);
            //аутентифицируем пользователя
            SecurityContextUtils.setAuthentication(user);
        }
        return userDtoRs;
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
