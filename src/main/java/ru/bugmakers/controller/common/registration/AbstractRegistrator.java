package ru.bugmakers.controller.common.registration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.Role;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.converters.User2UserDtoConverter;
import ru.bugmakers.mappers.converters.UserDtoToUserRegisterConverter;
import ru.bugmakers.mappers.enrichers.UserDTO2UserEnricher;
import ru.bugmakers.service.EmailService;
import ru.bugmakers.service.UserService;
import ru.bugmakers.utils.SecurityContextUtils;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Ivan
 */
public abstract class AbstractRegistrator implements Registrator {


    private UserService userService;
    private UserDtoToUserRegisterConverter userDtoToUserRegisterConverter;
    private User2UserDtoConverter user2UserDtoConverter;
    private UserDTO2UserEnricher userDTO2UserEnricher;
    private EmailService emailService;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    UserService getUserService() {
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
    public void setUserDTO2UserEnricher(UserDTO2UserEnricher userDTO2UserEnricher) {
        this.userDTO2UserEnricher = userDTO2UserEnricher;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO register(UserType userType, UserDTO userDto) throws MbException {

        String id = userDto.getId();

        User user = checkUserBySocial(id != null ? Long.valueOf(id) : null);

        //проверяем наличие телефона пользователя среди зарегистрированных
        if (userService.isExistsByPhone(userDto.getPhoneNumber()) || userService.isExistsByLogin(userDto.getPhoneNumber())) {
            throw MbException.create(MbError.RGE09);
        }

        if (user == null) {
            user = userDtoToUserRegisterConverter.convert(userDto);
        } else {
            userDTO2UserEnricher.enrich(userDto, user);
            user.setLogin(userDto.getPhoneNumber());
            user.setRegistrationDate(LocalDateTime.now());
            if (StringUtils.isNotBlank(userDto.getPassword())) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
            user.setPersonalDataConsent(Optional.ofNullable(userDto.getIsAgreementOfPersonalData()).orElse(false));
            user.setContractConsent(Optional.ofNullable(userDto.getIsArtistContract()).orElse(false));
            user.setReferrer(userDto.getReferrerId() != null ? userService.findUserById(userDto.getReferrerId()) : null);
        }
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

    /**
     * Проверка авторизации пользователя в социальной сети
     *
     * @param id идентификатор пользователя в БД
     * @return объект пользователя
     * @throws MbException ошибка в случае, если пользователь не авторизовывался в социальной сети
     */
    public abstract User checkUserBySocial(final Long id) throws MbException;

}
