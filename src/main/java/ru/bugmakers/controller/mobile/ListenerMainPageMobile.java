package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.mobile.UpdateMapRequestArtistOrListenerMobile;
import ru.bugmakers.dto.response.mobile.FindArtistResponseMobile;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;
import ru.bugmakers.dto.response.mobile.UpdateMapResponseMobile;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/mapi/")
public class ListenerMainPageMobile extends CommonController {
    @RequestMapping(method = RequestMethod.POST, value = "updateMap")
    public ResponseEntity<ResponseToMobile> updateMap(@RequestBody UpdateMapRequestArtistOrListenerMobile updateMapRequestListener) {
        UpdateMapResponseMobile updateMapResponseMobile = null;
        return ResponseEntity.ok().headers(responseHeaders).body(updateMapResponseMobile);
    }

    @RequestMapping(method = RequestMethod.GET, value = "findArtist")
    public ResponseEntity<ResponseToMobile> findArtist(@RequestParam("session_id") String sessionId,
                                                      @RequestParam("id") String id,
                                                      @RequestParam("alias_text") String aliasText) {
        FindArtistResponseMobile findArtistResponseMobile = null;
        return ResponseEntity.ok().headers(responseHeaders).body(findArtistResponseMobile);
    }

}
