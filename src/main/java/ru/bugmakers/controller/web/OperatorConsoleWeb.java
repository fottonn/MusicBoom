package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.ApproveTransactionRequestWeb;
import ru.bugmakers.dto.request.web.ClosedWithdrawListRequestWeb;
import ru.bugmakers.dto.request.web.OpenWithdrawListRequestWeb;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.OperatorService;

/**
 * Created by Ayrat on 08.12.2017.
 */
@RestController
@RequestMapping("/webapi/operator")
public class OperatorConsoleWeb extends MbController {
    private OperatorService operatorService;

    @Autowired
    public void setOperatorService(OperatorService operatorService) {
        this.operatorService = operatorService;
    }


    @PostMapping(value = "/getopenwithdrawlist")
    private ResponseEntity<MbResponseToWeb> getOpenWithdrawList(@RequestBody OpenWithdrawListRequestWeb openWithdrawListRequestWeb) {
        try {
            if (openWithdrawListRequestWeb.getPage() != null && openWithdrawListRequestWeb.getSize() != null) {
                operatorService.getOpenWithdawList(openWithdrawListRequestWeb.getPage(), openWithdrawListRequestWeb.getSize());
            }else{
                throw MbException.create(MbError.CME05);
            }
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToWeb(e, RsStatus.ERROR));
        } catch (Exception e){
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
    }

    @PostMapping(value = "/getclosedwithdrawlist")
    private ResponseEntity<MbResponseToWeb> getClosedWithdrawList(@RequestBody ClosedWithdrawListRequestWeb closedWithdrawListRequestWeb) {
    }

    @PostMapping(value = "/make")
    private ResponseEntity<MbResponseToWeb> makeTransaction(@AuthenticationPrincipal UserPrincipal user,
                                                            @RequestBody ApproveTransactionRequestWeb approveTransactionRequestWeb) {
        try {
            if (approveTransactionRequestWeb.getTransactionRequest() != null &&
                approveTransactionRequestWeb.getTransactionRequest().getTransactionId() != null){
                operatorService.makeTransaction(approveTransactionRequestWeb.getTransactionRequest().getTransactionId(),
                                                approveTransactionRequestWeb.getTransactionRequest().getTransactionStatus());
            }else{
                throw MbException.create(MbError.CME05);
            }
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToWeb(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }


}
