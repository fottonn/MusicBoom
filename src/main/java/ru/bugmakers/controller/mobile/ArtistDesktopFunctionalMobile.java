package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.PerformanceStartRequestMobile;
import ru.bugmakers.dto.request.mobile.PerformanceStartValidationRequestMobile;
import ru.bugmakers.dto.response.mobile.EndPerformanceResponseMobile;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.dto.response.mobile.PerformanceStartResponseMobile;
import ru.bugmakers.dto.response.mobile.ValidatePerformanceResponseMobile;
import ru.bugmakers.entity.Event;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.localpers.entity.ActiveEvent;
import ru.bugmakers.localpers.service.ActiveEventService;
import ru.bugmakers.mappers.converters.ActiveEvent2EventConverter;
import ru.bugmakers.service.TransactionService;
import ru.bugmakers.service.UserService;

import java.time.LocalDateTime;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist")
public class ArtistDesktopFunctionalMobile extends MbController {

    private ActiveEventService activeEventService;
    private UserService userService;
    private ActiveEvent2EventConverter activeEvent2EventConverter;
    private TransactionService transactionService;

    @Autowired
    public void setActiveEventService(ActiveEventService activeEventService) {
        this.activeEventService = activeEventService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setActiveEvent2EventConverter(ActiveEvent2EventConverter activeEvent2EventConverter) {
        this.activeEvent2EventConverter = activeEvent2EventConverter;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/performance.start")
    public ResponseEntity<MbResponseToMobile> startPerformance(@AuthenticationPrincipal UserPrincipal principal,
                                                               @RequestBody PerformanceStartRequestMobile rq) {
        PerformanceStartResponseMobile rs;
        try {
            ActiveEvent activeEvent = new ActiveEvent(
                    principal.getUser().getId(),
                    Double.valueOf(rq.getLongitude()),
                    Double.valueOf(rq.getLatitude()));
            activeEvent.setLat(Double.valueOf(rq.getLatitude()));
            activeEvent.setLng(Double.valueOf(rq.getLongitude()));
            activeEventService.saveActiveEvent(activeEvent);
            rs = new PerformanceStartResponseMobile(RsStatus.SUCCESS);
        } catch (Exception e) {
            rs = new PerformanceStartResponseMobile(RsStatus.ERROR);
        }
        return ResponseEntity.ok(rs);
    }

    @PostMapping(value = "performance.validation")
    public ResponseEntity<MbResponseToMobile> validationPerformance(@AuthenticationPrincipal UserPrincipal principal,
                                                                    @RequestBody PerformanceStartValidationRequestMobile rq) {
        ValidatePerformanceResponseMobile rs;

        try {

            Long userId = principal.getUser().getId();
            ActiveEvent activeEvent = activeEventService.getActiveEventByUserId(userId);
            activeEvent.setLng(Double.valueOf(rq.getLongitude()));
            activeEvent.setLat(Double.valueOf(rq.getLatitude()));
            activeEvent = activeEventService.saveActiveEvent(activeEvent);

            String currentEarnedMoney = transactionService.getReceivedMoneyForPeriod(
                    principal.getUser().getId(),
                    activeEvent.getBeginTime(),
                    LocalDateTime.now());

            rs = new ValidatePerformanceResponseMobile(RsStatus.SUCCESS);
            rs.setCurrentEarnedMoney(currentEarnedMoney);

        } catch (Exception e) {
            rs = new ValidatePerformanceResponseMobile(RsStatus.ERROR);
        }
        return ResponseEntity.ok(rs);
    }

    @PostMapping(value = "/performance.end")
    public ResponseEntity<MbResponseToMobile> endPerformance(@AuthenticationPrincipal UserPrincipal principal) {
        EndPerformanceResponseMobile rs;
        try {
            ActiveEvent activeEvent = activeEventService.getActiveEventByUserId(principal.getUser().getId());
            if (activeEvent == null) throw MbException.create(MbError.CME02);
            activeEvent.setEndTime(LocalDateTime.now());
            Event event = activeEvent2EventConverter.convert(activeEvent);
            User user = userService.findUserById(activeEvent.getUserId());
            event.setUser(user);
            user.getEvents().add(event);
            user = userService.saveUser(user);

            String earnedMoney = transactionService.getReceivedMoneyForPeriod(
                    user.getId(),
                    event.getStartDate(),
                    event.getEndDate());

            rs = new EndPerformanceResponseMobile(RsStatus.SUCCESS);
            rs.setEarnedMoney(earnedMoney);
        } catch (MbException e) {
            rs = new EndPerformanceResponseMobile(e, RsStatus.ERROR);
        } catch (Exception e) {
            rs = new EndPerformanceResponseMobile(RsStatus.ERROR);
        }
        return ResponseEntity.ok(rs);
    }

}
