package ru.bugmakers.mappers.enrichers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.Email;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.NameRepresentation;
import ru.bugmakers.enums.Sex;

import java.time.LocalDate;
import java.util.Arrays;

import static java.util.Optional.ofNullable;
import static ru.bugmakers.utils.DateTimeFormatters.DATE_FORMATTER;

/**
 * Created by Ayrat on 23.01.2018.
 */
@Component
public class UserDTO2UserEnricher implements MBEnricher<UserDTO, User> {

    private UserDto2ArtistInfoEnricher userDto2ArtistInfoEnricher;

    @Autowired
    public void setUserDto2ArtistInfoEnricher(UserDto2ArtistInfoEnricher userDto2ArtistInfoEnricher) {
        this.userDto2ArtistInfoEnricher = userDto2ArtistInfoEnricher;
    }

    @Override
    public void enrich(UserDTO source, User target) {

        target.setCity(ofNullable(source.getCity()).orElse(target.getCity()));
        target.setName(ofNullable(source.getName()).orElse(target.getName()));
        target.setSurName(ofNullable(source.getSurname()).orElse(target.getSurName()));
        if (source.getEmail() != null) {
            if (target.getEmail() == null || !source.getEmail().equalsIgnoreCase(target.getEmail().getValue()))
                target.setEmail(new Email(source.getEmail()));
        }
        target.setPatronymic(ofNullable(source.getPatronymic()).orElse(target.getPatronymic()));
        target.setAboutMe(ofNullable(source.getAboutMe()).orElse(target.getAboutMe()));
        target.setNickname(ofNullable(source.getNickname()).orElse(target.getNickname()));
        target.setBirthDay(ofNullable(LocalDate.parse(source.getBirthday(), DATE_FORMATTER)).orElse(target.getBirthDay()));
        target.setCountry(ofNullable(source.getCountry()).orElse(target.getCountry()));
        target.setCity(ofNullable(source.getCity()).orElse(target.getCity()));
        target.setSex(Arrays.stream(Sex.values()).anyMatch(sex -> sex.name().equalsIgnoreCase(source.getSex())) ?
                Sex.valueOf(source.getSex().toUpperCase()) : target.getSex());
        target.setVkContact(ofNullable(source.getVk()).orElse(target.getVkContact()));
        target.setTlgContact(ofNullable(source.getTlg()).orElse(target.getTlgContact()));
        target.setWhatsappContact(ofNullable(source.getWapp()).orElse(target.getWhatsappContact()));
        target.setNameRepresentation(Arrays.stream(NameRepresentation.values())
                .anyMatch(nameRepresentation -> nameRepresentation.name().equalsIgnoreCase(source.getNameRepresentation())) ?
                NameRepresentation.valueOf(source.getNameRepresentation().toUpperCase()) : target.getNameRepresentation());
        userDto2ArtistInfoEnricher.enrich(source, target.getArtistInfo());
    }

}
