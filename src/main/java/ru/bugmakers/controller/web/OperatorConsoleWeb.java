package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.web.ApproveTransactionRequestWeb;
import ru.bugmakers.dto.request.web.ClosedWithdrawListRequestWeb;
import ru.bugmakers.dto.request.web.OpenWithdrawListRequestWeb;
import ru.bugmakers.dto.response.web.*;

/**
 * Created by Ayrat on 08.12.2017.
 */
@RestController
@RequestMapping("/webapi/operator/")
public class OperatorConsoleWeb extends CommonController {

    @RequestMapping(method = RequestMethod.GET, value = "getopenwithdrawlist/getcount")
    private ResponseEntity<ResponseToWeb> getOpenWithdrawListCount(@RequestBody OpenWithdrawListRequestWeb openWithdrawListRequestWeb) {
        OpenWithdrawListCountResponseWeb openWithdrawListCountResponseWeb = null;
        return ResponseEntity.ok(openWithdrawListCountResponseWeb);
    }

    @RequestMapping(method = RequestMethod.POST, value = "getopenwithdrawlist")
    private ResponseEntity<ResponseToWeb> getOpenWithdrawList(@RequestBody OpenWithdrawListRequestWeb openWithdrawListRequestWeb) {
        OpenWithdrawListResponseWeb openWithdrawListResponseWeb = null;
        return ResponseEntity.ok(openWithdrawListResponseWeb);
    }

    @RequestMapping(method = RequestMethod.POST, value = "getclosedwithdrawlist/getcount")
    private ResponseEntity<ResponseToWeb> getClosedWithdrawListCount(@RequestBody ClosedWithdrawListRequestWeb closedWithdrawListRequestWeb) {
        ClosedWithdrawListCountResponseWeb closedWithdrawListCountResponseWeb = null;
        return ResponseEntity.ok(closedWithdrawListCountResponseWeb);
    }

    @RequestMapping(method = RequestMethod.POST, value = "getclosedwithdrawlist")
    private ResponseEntity<ResponseToWeb> getClosedWithdrawList(@RequestBody ClosedWithdrawListRequestWeb closedWithdrawListRequestWeb) {
        ClosedWithdrawListResponseWeb closedWithdrawListResponseWeb = null;
        return ResponseEntity.ok(closedWithdrawListResponseWeb);
    }

    @RequestMapping(method = RequestMethod.POST, value = "approve")
    private ResponseEntity<ResponseToWeb> approveTransaction(@RequestBody ApproveTransactionRequestWeb approveTransactionRequestWeb) {
        ApproveTransactionResponseWeb approveTransactionResponseWeb = null;
        return ResponseEntity.ok(approveTransactionResponseWeb);
    }


}
