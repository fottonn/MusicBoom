package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.request.web.ArtistProfileEditingRequest;
import ru.bugmakers.dto.response.ResponseToMobile;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/webapi/artist/")
public class ArtistProfileWeb {
    @RequestMapping(method = RequestMethod.POST, value = "editing/artist.personal")
    public ResponseEntity<ResponseToMobile> artistProfileEdit(@RequestBody ArtistProfileEditingRequest artistProfileEditing) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }

}
