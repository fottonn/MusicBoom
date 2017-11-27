package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.CardInfoDto;
import ru.bugmakers.dto.ResponseToMobile;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/")
public class ArtistFinanceManagement {
    @RequestMapping(method = RequestMethod.POST, value = "card.attach")
    public ResponseEntity<ResponseToMobile> cardAttach(@RequestBody CardInfoDto cardInfoDto) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "card.update")
    public ResponseEntity<ResponseToMobile> cardUpdate(@RequestBody CardInfoDto cardInfoDto) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "card.detach")
    public ResponseEntity<ResponseToMobile> cardDetach(@RequestParam("session_id") String sessionId,
                                                       @RequestParam("id") String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "card.detach")
    public ResponseEntity<ResponseToMobile> withdraw(@RequestParam("session_id") String sessionId,
                                                     @RequestParam("id") String id,
                                                     @RequestParam("sum") String summ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
}
