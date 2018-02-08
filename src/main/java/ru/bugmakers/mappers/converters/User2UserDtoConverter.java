package ru.bugmakers.mappers.converters;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.utils.DateTimeFormatters;

import java.util.ArrayList;

/**
 * Created by Ivan
 */
@Component
public class User2UserDtoConverter implements MbConverter<User, UserDTO>, DateTimeFormatters {

    @Override
    public UserDTO convert(User source) {
        if (source == null) return null;
        return new UserDTO()
                .withUserType(source.getUserType().name())
                .withId(source.getId().toString())
                .withName(source.getName())
                .withSurname(source.getSurName())
                .withPatronimyc(source.getPatronymic())
                .withBirthday(source.getBirthDay() != null ? source.getBirthDay().format(DATE_FORMATTER) : null)
                .withSex(source.getSex() != null ? source.getSex().name() : null)
                .withNickname(source.getNickname())
                .withCountry(source.getCountry())
                .withCity(source.getCity())
                .withPhoneNumber(source.getPhone())
                .withEmail(source.getEmail() != null ? source.getEmail().getValue() : null)
                .withCreativity(source.getArtistInfo() != null ? source.getArtistInfo().getCreativity() : null)
                .withInstrument(source.getArtistInfo() != null ? source.getArtistInfo().getInstrument() : null)
                .withGenre(source.getArtistInfo() != null && source.getArtistInfo().getGenre() != null ? source.getArtistInfo().getGenre().name() : null)
                .withVk(source.getVkContact())
                .withTlg(source.getTlgContact())
                .withWapp(source.getWhatsappContact())
                .withIsOrdered(source.getArtistInfo() != null ? source.getArtistInfo().getOrdered() : null)
                .withRegDate(source.getRegistrationDate() != null ? source.getRegistrationDate().format(DATE_TIME_FORMATTER) : null)
                //.withAllEarnedMoney()
                //.withAllDerivedMoney()
                .withCityRating(source.getArtistRating() != null ? source.getArtistRating().getCityRatng() : null)
                .withCountryRating(source.getArtistRating() != null ? source.getArtistRating().getCountryRating() : null)
                //.withCurrentBalance()
                //.withAllDonatedArtists()
                //.withIsLinkedCard()
                //.withStatOfPerfomance()
                .withAllowOfPersonalData(source.getIsAllowOfPersonalData())
                .withArtistContact(source.getIsArtistContact())
                .withAvatar(source.getAvatar())
                .withPhotos(CollectionUtils.isNotEmpty(source.getPhotos()) ? new ArrayList<>(source.getPhotos()) : null);
    }

}
