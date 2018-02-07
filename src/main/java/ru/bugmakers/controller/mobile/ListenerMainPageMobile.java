package ru.bugmakers.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.MapPerformersRequest;
import ru.bugmakers.dto.response.mobile.FindArtistResponseMobile;
import ru.bugmakers.dto.response.mobile.MapPerformersResponseMobile;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/mapi")
public class ListenerMainPageMobile extends MbController {

    @PostMapping(value = "/map.performers")
    public ResponseEntity<MbResponseToMobile> updateMap(@RequestBody MapPerformersRequest updateMapRequestListener) {
        MapPerformersResponseMobile mapPerformersResponseMobile = null;
        return ResponseEntity.ok(mapPerformersResponseMobile);
    }

    @GetMapping(value = "/findArtist")
    public ResponseEntity<MbResponseToMobile> findArtist(@RequestParam("session_id") String sessionId,
                                                         @RequestParam("id") String id,
                                                         @RequestParam("alias_text") String aliasText) {
        FindArtistResponseMobile findArtistResponseMobile = null;
        return ResponseEntity.ok(findArtistResponseMobile);
    }

}
