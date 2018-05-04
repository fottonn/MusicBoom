package ru.bugmakers.mappers.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.StatOfPerformanceDTO;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.NameRepresentation;
import ru.bugmakers.service.EventService;
import ru.bugmakers.service.ImagesService;
import ru.bugmakers.service.PhotoService;
import ru.bugmakers.service.TransactionService;

import static ru.bugmakers.utils.DateTimeFormatters.DATE_FORMATTER;

/**
 * Created by Ivan
 */
@Component
public class User2UserDtoConverter implements MbConverter<User, UserDTO> {

    private TransactionService transactionService;
    private EventService eventService;
    private PhotoService photoService;
    private ImagesService imagesService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Autowired
    public void setImagesService(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @Override
    public UserDTO convert(User source) {
        if (source == null) return null;
        return new UserDTO()
                .withUserType(source.getUserType() != null ? source.getUserType().name() : null)
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
                .withGenre(source.getArtistInfo() != null ? source.getArtistInfo().getGenre() : null)
                .withVk(source.getVkContact())
                .withTlg(source.getTlgContact())
                .withWapp(source.getWhatsappContact())
                .withOrderable(source.getArtistInfo() != null && source.getArtistInfo().isOrderable())
                .withRegDate(source.getRegistrationDate() != null ? source.getRegistrationDate().format(DATE_FORMATTER) : null)
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
                .withAvatar(imagesService.fullImagePath(source.getAvatar()))
                .withCardNumber(source.getCardNumber() != null ? getMappedCardNumber(source.getCardNumber()) : null)
                .withPhotos(photoService.getPhotosByUserId(source.getId()))
                .withRegistered(source.isRegistered())
                .withNameRepresentation(source.getNameRepresentation() != null ? source.getNameRepresentation() : NameRepresentation.NICKNAME)
                ;
    }

    private String getMappedCardNumber(String cardNumber) {
        char[] chars = cardNumber.toCharArray();
        for (int i = 4; i < 12; i++) {
            chars[i] = '*';
        }
        return new StringBuilder().append(chars).toString();
    }

    private StatOfPerformanceDTO getStatOfPerformance(Long id) {
        if (id == null) return null;
        return new StatOfPerformanceDTO(
                eventService.getAllEvents(id),
                eventService.getHoursOfCurrentMonth(id),
                eventService.getMoneyOfCurrentMonth(id),
                eventService.getAverageEventTime(id));
    }

}
