package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.response.mobile.GetArtistResponseMobile;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;
import ru.bugmakers.dto.request.mobile.TransactionRequestMobile;
import ru.bugmakers.dto.response.mobile.TransactionResponseMobile;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/")
public class ArtistInfoPageMobile extends CommonController {
    @RequestMapping(method = RequestMethod.GET, value = "getArtist")
    public ResponseEntity<ResponseToMobile> getArtist(@RequestParam("session_id") String sessionId,
                                                   @RequestParam("id") String id,
                                                   @RequestParam("artist_id") String artistId) {
        GetArtistResponseMobile getArtistResponseMobile = null;
        return ResponseEntity.ok().headers(responseHeaders).body(getArtistResponseMobile);
    }
    @RequestMapping(method = RequestMethod.GET, value = "transaction")
    public ResponseEntity<ResponseToMobile> transaction(@RequestBody TransactionRequestMobile transactionRequestMobile) {
        TransactionResponseMobile transactionResponseMobile = null;
        return ResponseEntity.ok().headers(responseHeaders).body(transactionResponseMobile);
    }

}

