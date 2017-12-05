package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;
import ru.bugmakers.dto.request.mobile.TransactionRequest;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/")
public class ArtistInfoPageMobile {
    @RequestMapping(method = RequestMethod.GET, value = "getArtist")
    public ResponseEntity<ResponseToMobile> getArtist(@RequestParam("session_id") String sessionId,
                                                   @RequestParam("id") String id,
                                                   @RequestParam("artist_id") String artistId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "getArtist")
    public ResponseEntity<ResponseToMobile> transaction(@RequestBody TransactionRequest transactionRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }

}

