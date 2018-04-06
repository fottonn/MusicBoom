package ru.bugmakers.mappers.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.Email;
import ru.bugmakers.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static ru.bugmakers.utils.DateTimeFormatters.DATE_FORMATTER;

/**
 * Created by Ayrat on 27.12.2017.
 */
@Component
public class UserDtoToUserRegisterConverter implements MbConverter<UserDTO, User> {

    private PasswordEncoder passwordEncoder;
    private UserDto2ArtistInfoConverter userDto2ArtistInfoConverter;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDto2ArtistInfoConverter(UserDto2ArtistInfoConverter userDto2ArtistInfoConverter) {
        this.userDto2ArtistInfoConverter = userDto2ArtistInfoConverter;
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
                .withPatronymic(source.getPatronymic())
                .withNickname(source.getNickname())
                .withBirthDay(source.getBirthday() != null ? LocalDate.parse(source.getBirthday(), DATE_FORMATTER) : null)
                .withPhone(source.getPhoneNumber())
                .withCountry(source.getCountry())
                .withCity(source.getCity())
                .withRegistrationDate(LocalDateTime.now())
                .withPublicName(source.getNickname())
                .withArtistInfo(userDto2ArtistInfoConverter.convert(source))
                .withVkContact(source.getVk())
                .withTlgContact(source.getTlg())
                .withWhatsappContact(source.getWapp())
                .withPersonalDataConsent(Optional.ofNullable(source.getIsAgreementOfPersonalData()).orElse(false))
                .withContractConsent(Optional.ofNullable(source.getIsArtistContract()).orElse(false));
    }
}
