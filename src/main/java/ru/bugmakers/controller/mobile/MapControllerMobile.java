package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.ArtistsLocation;
import ru.bugmakers.dto.request.mobile.MapPerformersRequest;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.dto.response.mobile.MapPerformersResponseMobile;
import ru.bugmakers.localpers.entity.ActiveEvent;
import ru.bugmakers.localpers.service.ActiveEventService;
import ru.bugmakers.mappers.converters.ActiveEvent2ArtistLocationConverter;
import ru.bugmakers.utils.GeoLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/mapi")
public class MapControllerMobile {

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

    @PostMapping(value = "/map.performers")
    public ResponseEntity<MbResponse> mapPerformersInRadius(@RequestBody MapPerformersRequest rq) {

        MapPerformersResponseMobile rs;
        try {
            double radius = Double.parseDouble(rq.getRadius());
            double lng = Double.parseDouble(rq.getLongitude());
            double lat = Double.parseDouble(rq.getLatitude());
            GeoLocation curPosition = GeoLocation.fromDegrees(lat, lng);

            List<ActiveEvent> activeEvents = activeEventService.getActiveEventsInRadius(curPosition, radius);

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
