package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.request.mobile.CardInfoRequestMobile;
import ru.bugmakers.dto.response.mobile.FinanceManagementResponseMobile;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/")
public class ArtistFinanceManagementMobile {
    @RequestMapping(method = RequestMethod.POST, value = "card.attach")
    public ResponseEntity<ResponseToMobile> cardAttach(@RequestBody CardInfoRequestMobile cardInfoRequestMobile) {
        HttpHeaders responseHeaders = new HttpHeaders();
        FinanceManagementResponseMobile financeManagementResponseMobile = null;
        return new ResponseEntity<ResponseToMobile>(financeManagementResponseMobile, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "card.update")
    public ResponseEntity<ResponseToMobile> cardUpdate(@RequestBody CardInfoRequestMobile cardInfoRequestMobile) {
        HttpHeaders responseHeaders = new HttpHeaders();
        FinanceManagementResponseMobile financeManagementResponseMobile = null;
        return new ResponseEntity<ResponseToMobile>(financeManagementResponseMobile, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "card.detach")
    public ResponseEntity<ResponseToMobile> cardDetach(@RequestParam("session_id") String sessionId,
                                                       @RequestParam("id") String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        FinanceManagementResponseMobile financeManagementResponseMobile = null;
        return new ResponseEntity<ResponseToMobile>(financeManagementResponseMobile, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "card.detach")
    public ResponseEntity<ResponseToMobile> withdraw(@RequestParam("session_id") String sessionId,
                                                     @RequestParam("id") String id,
                                                     @RequestParam("sum") String summ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        FinanceManagementResponseMobile financeManagementResponseMobile = null;
        return new ResponseEntity<ResponseToMobile>(financeManagementResponseMobile, responseHeaders, HttpStatus.OK);
    }
}