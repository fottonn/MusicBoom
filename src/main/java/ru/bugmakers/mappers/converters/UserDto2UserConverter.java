package ru.bugmakers.mappers.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.Email;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.Role;
import ru.bugmakers.enums.Sex;
import ru.bugmakers.enums.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static ru.bugmakers.utils.DateTimeFormatters.DATE_FORMATTER;

/**
 * Created by Ayrat on 27.12.2017.
 */
@Component
public class UserDto2UserConverter implements MbConverter<UserDTO, User> {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User convert(UserDTO source) {
        User user = new User();
        user.setCity(source.getCity());
        user.setName(source.getName());
        user.setSurName(source.getSurname());
        user.setPatronymic(source.getPatronimyc());
        user.setAboutMe(source.getAboutMe());
        user.setEmail(new Email(source.getEmail()));
        user.setLogin(source.getEmail());
        user.setPassword(passwordEncoder.encode(source.getPassword()));
        user.setPatronymic(source.getPatronimyc());
        user.setNickname(source.getNickname());
        user.setBirthDay(LocalDate.parse(source.getBirthday(), DATE_FORMATTER));
        user.setPhone(source.getPhoneNumber());
        user.setCountry(source.getCountry());
        user.setCity(source.getCity());
        user.setRegistrationDate(LocalDateTime.now());
        user.setPublicName(source.getNickname());
        user.setUserType(UserType.ARTIST);
        user.setSex(Sex.NONE);
        user.setRoles(Role.ARTIST);
        user.setVkContact(source.getVk());
        user.setTlgContact(source.getTlg());
        user.setWhatsappContact(source.getWapp());
        user.setPersonalDataConsent(source.getIsAllowOfPersonalData());
        user.setContractConsent(source.getIsArtistContact());
        return user;
    }
}
