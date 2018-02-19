package ru.bugmakers.mappers.enrichers;

import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.Email;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.Sex;

import java.time.LocalDate;
import java.util.Optional;

import static ru.bugmakers.utils.DateTimeFormatters.DATE_FORMATTER;

/**
 * Created by Ayrat on 23.01.2018.
 */
@Component
public class UserDTO2UserEnricher implements MBEnricher<UserDTO, User> {
    @Override
    public void enrich(UserDTO source, User target) {

        target.setCity(Optional.ofNullable(source.getCity()).orElse(target.getCity()));
        target.setName(Optional.ofNullable(source.getName()).orElse(target.getName()));
        target.setSurName(Optional.ofNullable(source.getSurname()).orElse(target.getSurName()));
        if (source.getEmail() != null) {
            if (target.getEmail() == null || !target.getEmail().getValue().equals(source.getEmail()))
                target.setEmail(new Email(source.getEmail()));
        }
        target.setPatronymic(Optional.ofNullable(source.getPatronimyc()).orElse(target.getPatronymic()));
        target.setPatronymic(Optional.ofNullable(source.getAboutMe()).orElse(target.getAboutMe()));
        target.setNickname(Optional.ofNullable(source.getNickname()).orElse(target.getNickname()));
        target.setBirthDay(Optional.ofNullable(LocalDate.parse(source.getBirthday(), DATE_FORMATTER)).orElse(target.getBirthDay()));
        target.setCountry(Optional.ofNullable(source.getCountry()).orElse(target.getCountry()));
        target.setCity(Optional.ofNullable(source.getCity()).orElse(target.getCity()));
        target.setPublicName(Optional.ofNullable(source.getNickname()).orElse(target.getPublicName()));
        try {
            target.setSex(Sex.valueOf(source.getSex()));
        } catch (IllegalArgumentException e) {
            target.setSex(target.getSex());
        }
        target.setVkContact(Optional.ofNullable(source.getVk()).orElse(target.getVkContact()));
        target.setTlgContact(Optional.ofNullable(source.getTlg()).orElse(target.getTlgContact()));
        target.setWhatsappContact(Optional.ofNullable(source.getWapp()).orElse(target.getWhatsappContact()));
    }
}
