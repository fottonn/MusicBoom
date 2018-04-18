package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.ArtistsLocation;
import ru.bugmakers.dto.request.web.TransactionWebRq;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.dto.response.mobile.MapPerformersResponseMobile;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.localpers.entity.ActiveEvent;
import ru.bugmakers.localpers.service.ActiveEventService;
import ru.bugmakers.mappers.converters.ActiveEvent2ArtistLocationConverter;
import ru.bugmakers.service.TransactionService;
import ru.bugmakers.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/")
public class AnonymousWeb {


    private ActiveEventService activeEventService;
    private ActiveEvent2ArtistLocationConverter activeEvent2ArtistLocationConverter;

    @Autowired
    public void setActiveEventService(ActiveEventService activeEventService) {
        this.activeEventService = activeEventService;
    }

    @Autowired
    public void setActiveEvent2ArtistLocationConverter(ActiveEvent2ArtistLocationConverter activeEvent2ArtistLocationConverter) {
        this.activeEvent2ArtistLocationConverter = activeEvent2ArtistLocationConverter;
    }

    @GetMapping("performers")
    public ResponseEntity<MbResponse> mapPerformance() {

        MapPerformersResponseMobile rs;

        try {
            List<ActiveEvent> activeEvents = activeEventService.getAllActiveEvents();
            List<ArtistsLocation> artists = new ArrayList<>(activeEvents.size());
            for (ActiveEvent ae : activeEvents) {
                artists.add(activeEvent2ArtistLocationConverter.convert(ae));
            }
            rs = new MapPerformersResponseMobile();
            rs.setArtists(artists);
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

}
