package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.ApproveTransactionRequestWeb;
import ru.bugmakers.dto.request.web.ClosedWithdrawListRequestWeb;
import ru.bugmakers.dto.request.web.OpenWithdrawListRequestWeb;
import ru.bugmakers.dto.response.web.*;

/**
 * Created by Ayrat on 08.12.2017.
 */
@RestController
@RequestMapping("/webapi/operator/")
public class OperatorConsoleWeb extends MbController {

    @GetMapping(value = "getopenwithdrawlist/getcount")
    private ResponseEntity<MbResponseToWeb> getOpenWithdrawListCount(@RequestBody OpenWithdrawListRequestWeb openWithdrawListRequestWeb) {
        OpenWithdrawListCountResponseWeb openWithdrawListCountResponseWeb = null;
        return ResponseEntity.ok(openWithdrawListCountResponseWeb);
    }

    @PostMapping(value = "getopenwithdrawlist")
    private ResponseEntity<MbResponseToWeb> getOpenWithdrawList(@RequestBody OpenWithdrawListRequestWeb openWithdrawListRequestWeb) {
        OpenWithdrawListResponseWeb openWithdrawListResponseWeb = null;
        return ResponseEntity.ok(openWithdrawListResponseWeb);
    }

    @PostMapping(value = "getclosedwithdrawlist/getcount")
    private ResponseEntity<MbResponseToWeb> getClosedWithdrawListCount(@RequestBody ClosedWithdrawListRequestWeb closedWithdrawListRequestWeb) {
        ClosedWithdrawListCountResponseWeb closedWithdrawListCountResponseWeb = null;
        return ResponseEntity.ok(closedWithdrawListCountResponseWeb);
    }

    @PostMapping(value = "getclosedwithdrawlist")
    private ResponseEntity<MbResponseToWeb> getClosedWithdrawList(@RequestBody ClosedWithdrawListRequestWeb closedWithdrawListRequestWeb) {
        ClosedWithdrawListResponseWeb closedWithdrawListResponseWeb = null;
        return ResponseEntity.ok(closedWithdrawListResponseWeb);
    }

    @PostMapping(value = "approve")
    private ResponseEntity<MbResponseToWeb> approveTransaction(@RequestBody ApproveTransactionRequestWeb approveTransactionRequestWeb) {
        ApproveTransactionResponseWeb approveTransactionResponseWeb = null;
        return ResponseEntity.ok(approveTransactionResponseWeb);
    }


}
