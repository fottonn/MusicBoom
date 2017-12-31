package ru.bugmakers.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.mobile.CardInfoRequestMobile;
import ru.bugmakers.dto.response.mobile.FinanceManagementResponseMobile;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/")
public class ArtistFinanceManagementMobile extends CommonController {

    @PostMapping(value = "card.attach")
    public ResponseEntity<ResponseToMobile> cardAttach(@RequestBody CardInfoRequestMobile cardInfoRequestMobile) {

        FinanceManagementResponseMobile financeManagementResponseMobile = null;
        return ResponseEntity.ok(financeManagementResponseMobile);
    }

    @PostMapping(value = "card.update")
    public ResponseEntity<ResponseToMobile> cardUpdate(@RequestBody CardInfoRequestMobile cardInfoRequestMobile) {

        FinanceManagementResponseMobile financeManagementResponseMobile = null;
        return ResponseEntity.ok(financeManagementResponseMobile);
    }

    @GetMapping(value = "card.detach")
    public ResponseEntity<ResponseToMobile> cardDetach(@RequestParam("session_id") String sessionId,
                                                       @RequestParam("id") String id) {

        FinanceManagementResponseMobile financeManagementResponseMobile = null;
        return ResponseEntity.ok(financeManagementResponseMobile);
    }

    @GetMapping(value = "withdraw")
    public ResponseEntity<ResponseToMobile> withdraw(@RequestParam("session_id") String sessionId,
                                                     @RequestParam("id") String id,
                                                     @RequestParam("sum") String summ) {

        FinanceManagementResponseMobile financeManagementResponseMobile = null;
        return ResponseEntity.ok(financeManagementResponseMobile);
    }
}
