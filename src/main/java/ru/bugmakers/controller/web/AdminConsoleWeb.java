package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.request.web.*;
import ru.bugmakers.dto.response.web.ResponseToWeb;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi/admin")
public class AdminConsoleWeb {
    @RequestMapping(method = RequestMethod.POST, value = "/getartistinfo")
    public ResponseEntity<ResponseToWeb> getArtistInfo(@RequestBody ArtistInfoRequest artistInfoRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.block")
    public ResponseEntity<ResponseToWeb> blockArtist(@RequestBody ArtistBlockRequest artistBlockRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.delete")
    public ResponseEntity<ResponseToWeb> deleteArtist(@RequestBody ArtistDeleteRequest artistDeleteRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/sendmessage")
    public ResponseEntity<ResponseToWeb> sendMessage(@RequestBody SendMessageRequest sendMessagerequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.stat")
    public ResponseEntity<ResponseToWeb> getArtistStatistic(@RequestBody ArtistStatisticRequest artistStatisticRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.stat.period")
    public ResponseEntity<ResponseToWeb> getArtistStatisticWithPeriod(@RequestBody ArtistStatisticWithPeriodRequest artistStatisticWithPeriodRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }

}
