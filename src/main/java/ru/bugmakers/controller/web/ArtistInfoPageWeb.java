package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.response.web.ArtistInfoResponseWeb;
import ru.bugmakers.dto.response.web.ResponseToWeb;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi")
public class ArtistInfoPageWeb {
    @RequestMapping(method = RequestMethod.GET, value = "/getArtistInfo")
    public ResponseEntity<ResponseToWeb> ArtistWebAuthentication(@RequestParam("artistId") String artistId) {
        ArtistInfoResponseWeb artistInfoResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistInfoResponseWeb, responseHeaders, HttpStatus.OK);
    }
}
