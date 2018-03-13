package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.web.*;
import ru.bugmakers.dto.response.web.ArtistListWebRs;
import ru.bugmakers.dto.response.web.ArtistStatisticForAdminWebRs;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.mappers.converters.User2UserDtoConverter;
import ru.bugmakers.mappers.enrichers.UserDTO2UserEnricher;
import ru.bugmakers.service.EmailService;
import ru.bugmakers.service.EventService;
import ru.bugmakers.service.TransactionService;
import ru.bugmakers.service.UserService;

import java.time.LocalDateTime;

import static ru.bugmakers.utils.DateTimeFormatters.parseLocalDateTime;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi/admin")
public class AdminConsoleWeb extends MbController {

    private UserService userService;
    private EmailService emailService;
    private TransactionService transactionService;
    private EventService eventService;
    private UserDTO2UserEnricher userDTO2UserEnricher;
    private User2UserDtoConverter user2UserDtoConverter;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setUserDTO2UserEnricher(UserDTO2UserEnricher userDTO2UserEnricher) {
        this.userDTO2UserEnricher = userDTO2UserEnricher;
    }

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }

    @PostMapping(value = "/artist.list")
    public ResponseEntity<MbResponseToWeb> getArtistList(@RequestBody ArtistListWebRq rq) {
        ArtistListWebRs rs;
        try {
            int page = Integer.parseInt(rq.getPage()) - 1;
            int size = Integer.parseInt(rq.getSize());
            Page<User> users = userService.findAllUsersByUserType(UserType.ARTIST, PageRequest.of(page, size, Sort.by("id")));
            Page<UserDTO> artistsPage = users.map(user -> user2UserDtoConverter.convert(user));
            rs = new ArtistListWebRs(RsStatus.SUCCESS);
            rs.setArtists(artistsPage.getContent());
            rs.setPage(artistsPage.getNumber() + 1);
            rs.setPageSize(artistsPage.getSize());
            rs.setArtistCountInPage(artistsPage.getNumberOfElements());
            rs.setTotalPages(artistsPage.getTotalPages());
            rs.setTotalArtists(artistsPage.getTotalElements());
        } catch (Exception e) {
            rs = new ArtistListWebRs(RsStatus.ERROR);
        }
        return ResponseEntity.ok(rs);
    }

    @PostMapping(value = "/artist.edit")
    public ResponseEntity<MbResponseToWeb> editArtist(@RequestBody ArtistEditWebRq rq) {
        try {
            User editedArtist = userService.findUserById(Long.valueOf(rq.getArtist().getId()));
            userDTO2UserEnricher.enrich(rq.getArtist(), editedArtist);
            userService.updateUser(editedArtist);
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/artist.block")
    public ResponseEntity<MbResponseToWeb> blockArtist(@RequestBody ArtistBlockWebRq rq) {

        try {
            User blockedArtist = userService.findUserById(Long.valueOf(rq.getArtistId()));
            blockedArtist.setEnabled(!rq.getBlock());
            userService.updateUser(blockedArtist);
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/artist.delete")
    public ResponseEntity<MbResponseToWeb> deleteArtist(@RequestBody ArtistDeleteWebRq rq) {

        try {
            userService.deleteUserById(Long.valueOf(rq.getArtist().getId()));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/message.send")
    public ResponseEntity<MbResponseToWeb> sendMessage(@RequestBody SendMessageWebRq rq) {

        try {
            emailService.sendEmailToAllArtists(rq.getMessage(), rq.getSubject());
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/artist.stat")
    public ResponseEntity<MbResponseToWeb> getArtistStatistic(@RequestBody ArtistStatisticWebRq rq) {
        ArtistStatisticForAdminWebRs rs;
        try {
            rs = new ArtistStatisticForAdminWebRs(RsStatus.SUCCESS);
            Long id = Long.valueOf(rq.getArtist().getId());
            rs.setId(String.valueOf(id));
            rs.setDonated(transactionService.getAllReceivedMoney(id));
            rs.setCashout(transactionService.getAllDerivedMoney(id));
            rs.setBalance(transactionService.getCurrentBalance(id));
            rs.setShowTime(eventService.getTotalEventsTime(id));
        } catch (Exception e) {
            rs = new ArtistStatisticForAdminWebRs(RsStatus.ERROR);
        }
        return ResponseEntity.ok(rs);
    }

    @PostMapping(value = "/artist.stat.period")
    public ResponseEntity<MbResponseToWeb> getArtistStatisticWithPeriod(@RequestBody ArtistStatisticWithPeriodWebRq rq) {
        ArtistStatisticForAdminWebRs rs;
        try {
            rs = new ArtistStatisticForAdminWebRs(RsStatus.SUCCESS);
            final Long id = Long.valueOf(rq.getArtist().getId());
            final LocalDateTime start = parseLocalDateTime(rq.getPeriod().getStart());
            final LocalDateTime end = parseLocalDateTime(rq.getPeriod().getEnd());
            rs.setId(String.valueOf(id));
            rs.setDonated(transactionService.getReceivedMoneyForPeriod(id, start, end));
            rs.setCashout(transactionService.getDerivedMoneyForPeriod(id, start, end));
            rs.setBalance(transactionService.getCurrentBalance(id));
            rs.setShowTime(eventService.getPeriodEventsTime(id, start, end));
        } catch (Exception e) {
            rs = new ArtistStatisticForAdminWebRs(RsStatus.ERROR);
        }
        return ResponseEntity.ok(rs);
    }


}
