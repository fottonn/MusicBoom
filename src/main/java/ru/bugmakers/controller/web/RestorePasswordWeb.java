package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.request.web.ArtistRegistrationRequest;
import ru.bugmakers.dto.request.web.NewPasswordRequest;
import ru.bugmakers.dto.response.web.ResponseToWeb;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/webapi")
public class RestorePasswordWeb {
    @RequestMapping(method = RequestMethod.GET, value = "/restorepassword")
    public ResponseEntity<ResponseToWeb> restorePassword(@RequestParam("email") String email) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/codefromemail")
    public ResponseEntity<ResponseToWeb> chngPassword(@RequestParam("code") String code,
                                                      @RequestParam("user_id") String userId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "chngpassword")
    public ResponseEntity<ResponseToWeb> artistProfileEdit(@RequestBody NewPasswordRequest newPasswordRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }
}
