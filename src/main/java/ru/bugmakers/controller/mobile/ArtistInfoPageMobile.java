package ru.bugmakers.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.TransactionRequestMobile;
import ru.bugmakers.dto.response.mobile.GetArtistResponseMobile;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.dto.response.mobile.TransactionResponseMobile;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/")
public class ArtistInfoPageMobile extends MbController {

    @GetMapping(value = "getArtist")
    public ResponseEntity<MbResponseToMobile> getArtist(@RequestParam("session_id") String sessionId,
                                                        @RequestParam("id") String id,
                                                        @RequestParam("artist_id") String artistId) {
        GetArtistResponseMobile getArtistResponseMobile = null;
        return ResponseEntity.ok(getArtistResponseMobile);
    }

    @GetMapping(value = "transaction")
    public ResponseEntity<MbResponseToMobile> transaction(@RequestBody TransactionRequestMobile transactionRequestMobile) {
        TransactionResponseMobile transactionResponseMobile = null;
        return ResponseEntity.ok(transactionResponseMobile);
    }

}

