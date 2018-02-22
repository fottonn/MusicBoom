package ru.bugmakers.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.Email;
import ru.bugmakers.entity.User;
import ru.bugmakers.mappers.converters.MbConverter;
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
                .withRegistered(true)
                .withName(source.getName())
                .withSurName(source.getSurname())
                .withEmail(source.getEmail() != null ? new Email(source.getEmail()) : null)
                .withPhone(source.getPhoneNumber())
                .withLogin(source.getPhoneNumber())
                .withPassword(passwordEncoder.encode(source.getPassword()))
                .withPatronymic(source.getPatronimyc())
                .withNickname(source.getNickname())
                .withBirthDay(source.getBirthday() != null ? LocalDate.parse(source.getBirthday(), DATE_FORMATTER) : null)
                .withPhone(source.getPhoneNumber())
                .withCountry(source.getCountry())
                .withCity(source.getCity())
                .withRegistrationDate(LocalDateTime.now())
                .withPublicName(source.getNickname())
                .withVkContact(source.getVk())
                .withTlgContact(source.getTlg())
                .withWhatsappContact(source.getWapp())
                .withPersonalDataConsent(source.getIsAllowOfPersonalData())
                .withContractConsent(source.getIsArtistContact());
    }

}
