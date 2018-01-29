package ru.bugmakers.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.Sex;
import ru.bugmakers.utils.DateTimeFormatters;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Ayrat on 27.12.2017.
 */
@Component
public class UserDtoToUserRegisterConverter implements MbConverter<UserDTO, User>, DateTimeFormatters {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User convert(UserDTO source) {
        return new User()
                .withName(source.getName())
                .withSurName(source.getSurname())
                .withEmail(source.getEmail())
                .withLogin(source.getEmail())
                .withPassword(passwordEncoder.encode(source.getPassword()))
                .withPatronymic(source.getPatronimyc())
                .withNickname(source.getNickname())
                .withBirthDay(LocalDate.parse(source.getBirthday(), DATE_FORMATTER))
                .withPhone(source.getPhoneNumber())
                .withCountry(source.getCountry())
                .withCity(source.getCity())
                .withRegistrationDate(LocalDateTime.now())
                .withPublicName(source.getNickname())
                .withSex(Sex.NONE)
                .withVkContact(source.getVk())
                .withTlgContact(source.getTlg())
                .withWhatsappContact(source.getWapp())
                .withIsAllowOfPersonalData(source.getIsAllowOfPersonalData())
                .withIsArtistContact(source.getIsArtistContact());
    }
}
