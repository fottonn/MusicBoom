package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.request.web.ArtistRegistrationRequest;
import ru.bugmakers.dto.response.web.ResponseToWeb;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/webapi/artist/")
public class ArtistRegistrationWeb {
    @RequestMapping(method = RequestMethod.POST, value = "registration")
    public ResponseEntity<ResponseToWeb> artistProfileEdit(@RequestBody ArtistRegistrationRequest artistProfileEditing) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }
}
