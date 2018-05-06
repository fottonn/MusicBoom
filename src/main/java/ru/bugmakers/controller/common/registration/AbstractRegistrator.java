package ru.bugmakers.controller.common.registration;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.Role;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.converters.User2UserDtoConverter;
import ru.bugmakers.mappers.converters.UserDtoToUserRegisterConverter;
import ru.bugmakers.service.EmailService;
import ru.bugmakers.service.UserService;
import ru.bugmakers.utils.SecurityContextUtils;
import ru.bugmakers.utils.SocialIdChecker;

/**
 * Created by Ivan
 */
public abstract class AbstractRegistrator implements Registrator {

    private UserService userService;
    private UserDtoToUserRegisterConverter userDtoToUserRegisterConverter;
    private User2UserDtoConverter user2UserDtoConverter;
    private EmailService emailService;
    private SocialIdChecker socialIdChecker;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserDtoToUserRegisterConverter(UserDtoToUserRegisterConverter userDtoToUserRegisterConverter) {
        this.userDtoToUserRegisterConverter = userDtoToUserRegisterConverter;
    }

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void setSocialIdChecker(SocialIdChecker socialIdChecker) {
        this.socialIdChecker = socialIdChecker;
    }

    protected SocialIdChecker getSocialIdChecker() {
        return socialIdChecker;
    }

    @Override
    public UserDTO register(UserType userType, UserDTO userDto, String socialId, String token) throws MbException {

        //проверяем наличие телефона пользователя среди зарегистрированных
        if (userService.isExistsByPhone(userDto.getPhoneNumber()) || userService.isExistsByLogin(userDto.getPhoneNumber())) {
            throw MbException.create(MbError.RGE09);
        }

        //проверяем socialId на наличие в базе
        checkExistsSocialId(socialId);

        //проверяем валидность socialId
        if (!isValidSocialId(token, socialId)) {
            throw MbException.create(MbError.AUE16);
        }

        User user = userDtoToUserRegisterConverter.convert(userDto);
        user = setSocialAuth(user, socialId);
        user.setUserType(userType);
        switch (userType) {
            case ARTIST:
                user.setRoles(Role.ARTIST);
                break;
            case LISTENER:
                user.setRoles(Role.LISTENER);
                break;
        }
        user.setRegistered(true);
        user = userService.saveUser(user);
        SecurityContextUtils.setAuthentication(user);
        if (user.getEmail() != null && !user.getEmail().isEnabled()) {
            emailService.sendConfirmationEmail(user);
        }
        return user2UserDtoConverter.convert(user);
    }

    protected abstract boolean isValidSocialId(String token, String socialId);

    protected abstract void checkExistsSocialId(final String id) throws MbException;

    protected abstract User setSocialAuth(User user, String id);

}
