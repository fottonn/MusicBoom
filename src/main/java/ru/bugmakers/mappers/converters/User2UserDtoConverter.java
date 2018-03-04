package ru.bugmakers.mappers.converters;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.StatOfPerformanceDTO;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.service.EventService;
import ru.bugmakers.service.TransactionService;

import java.util.ArrayList;

import static ru.bugmakers.utils.DateTimeFormatters.DATE_FORMATTER;
import static ru.bugmakers.utils.DateTimeFormatters.DATE_TIME_FORMATTER;

/**
 * Created by Ivan
 */
@Component
public class User2UserDtoConverter implements MbConverter<User, UserDTO> {

    private TransactionService transactionService;
    private EventService eventService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public UserDTO convert(User source) {
        if (source == null) return null;
        return new UserDTO()
                .withUserType(source.getUserType().name())
                .withId(source.getId().toString())
                .withName(source.getName())
                .withSurname(source.getSurName())
                .withPatronimyc(source.getPatronymic())
                .withAboutMe(source.getAboutMe())
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
                .withAllEarnedMoney(transactionService.getAllReceivedMoney(source.getId()))
                .withAllDerivedMoney(transactionService.getAllDerivedMoney(source.getId()))
                .withCityRating(source.getArtistRating() != null ? source.getArtistRating().getCityRatng() : null)
                .withCountryRating(source.getArtistRating() != null ? source.getArtistRating().getCountryRating() : null)
                .withCurrentBalance(transactionService.getCurrentBalance(source.getId()))
                .withAllDonatedArtists(transactionService.allDonatedArtistCount(source.getId()))
                .withIsLinkedCard(source.isLinkedCard())
                .withStatOfPerformance(getStatOfPerformance(source.getId()))
                .withAllowOfPersonalData(source.isPersonalDataConsent())
                .withArtistContact(source.isContractConsent())
                .withAvatar(source.getAvatar())
                .withPhotos(CollectionUtils.isNotEmpty(source.getPhotos()) ? new ArrayList<>(source.getPhotos()) : null);
    }

    private StatOfPerformanceDTO getStatOfPerformance(Long id) {
        if (id == null) return null;
        return new StatOfPerformanceDTO(
                eventService.getAllEvents(id),
                eventService.getHoursOfMonth(id),
                eventService.getMoneyOfMonth(id),
                eventService.getAverageEventTime(id));
    }

}
