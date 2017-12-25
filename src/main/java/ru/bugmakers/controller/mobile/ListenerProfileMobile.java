package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.request.mobile.ListenerProfileRequest;
import ru.bugmakers.dto.response.mobile.ArtistEditingResponseMobile;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/mapi/listener/editing")
public class ListenerProfileMobile {
    @RequestMapping(method = RequestMethod.POST, value = "listener.editing")
    public ResponseEntity<ResponseToMobile> editProfile(@RequestBody ListenerProfileRequest listenerProfileRequest) {
        ArtistEditingResponseMobile listenerEditingResponse = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(listenerEditingResponse, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "attachCard")
    public ResponseEntity<ResponseToMobile> getArtist(@RequestParam("session_id") String sessionId,
                                                      @RequestParam("id") String id,
                                                      @RequestParam("is_attached") Boolean isAttached) {
        //TODO Пока не понятно какой будет ответ, необходимо изучить API
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
}
