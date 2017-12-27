package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.request.web.*;
import ru.bugmakers.dto.response.web.*;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi/admin")
public class AdminConsoleWeb {
    @RequestMapping(method = RequestMethod.POST, value = "/getartistlist")
    public ResponseEntity<ResponseToWeb> getArtistInfo(@RequestBody ArtistListRequest artistListRequest) {
        ArtistInfoFromAdminConsoleResponseWeb artistInfoFromAdminConsoleResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistInfoFromAdminConsoleResponseWeb, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.edit")
    public ResponseEntity<ResponseToWeb> editArtist(@RequestBody ArtistEditWebRequest artistBlockRequest) {
        ArtistEditingResponseWeb artistEditingResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistEditingResponseWeb, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.block")
    public ResponseEntity<ResponseToWeb> blockArtist(@RequestBody ArtistBlockRequest artistBlockRequest) {
        ArtistBlockResponseWeb artistBlockResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistBlockResponseWeb, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.delete")
    public ResponseEntity<ResponseToWeb> deleteArtist(@RequestBody ArtistDeleteRequest artistDeleteRequest) {
        ArtistDeleteResponseWeb artistDeleteResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistDeleteResponseWeb, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/sendmessage")
    public ResponseEntity<ResponseToWeb> sendMessage(@RequestBody SendMessageRequest sendMessagerequest) {
        SendMessageResponseMobile sendMessageResponseMobile = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(sendMessageResponseMobile, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.stat")
    public ResponseEntity<ResponseToWeb> getArtistStatistic(@RequestBody ArtistStatisticRequest artistStatisticRequest) {
        ArtistStatisticFromAdminConsoleResponseWeb artistStatisticFromAdminConsoleResponseWeb = null;

        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistStatisticFromAdminConsoleResponseWeb, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.stat.period")
    public ResponseEntity<ResponseToWeb> getArtistStatisticWithPeriod(@RequestBody ArtistStatisticWithPeriodRequest artistStatisticRequest) {
        ArtistStatisticFromAdminConsoleResponseWeb artistStatisticFromAdminConsoleResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistStatisticFromAdminConsoleResponseWeb, responseHeaders, HttpStatus.OK);
    }






}
