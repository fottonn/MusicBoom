package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.request.mobile.ListenerProfileDto;
import ru.bugmakers.dto.response.ResponseToMobile;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/mapi/listener/editing")
public class ListenerUserProfileMobile {
    @RequestMapping(method = RequestMethod.POST, value = "listeer.editing")
    public ResponseEntity<ResponseToMobile> editProfile(@RequestBody ListenerProfileDto listenerProfileDto) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "attachCard")
    public ResponseEntity<ResponseToMobile> getArtist(@RequestParam("session_id") String sessionId,
                                                      @RequestParam("id") String id,
                                                      @RequestParam("is_attached") Boolean isAttached) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
}
