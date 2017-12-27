package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.response.web.*;
import ru.bugmakers.dto.request.web.ApproveTransactionRequest;
import ru.bugmakers.dto.request.web.ClosedWithdrawListRequest;
import ru.bugmakers.dto.request.web.OpenWithdrawListRequest;

/**
 * Created by Ayrat on 08.12.2017.
 */
@RestController
@RequestMapping("/webapi/operator/")
public class OperatorConsoleWeb {
    @RequestMapping(method = RequestMethod.GET, value = "getopenwithdrawlist/getcount")
    private ResponseEntity<ResponseToWeb> getOpenWithdrawListCount(@RequestBody OpenWithdrawListRequest openWithdrawListRequest){
        OpenWithdrawListCountResponseWeb openWithdrawListCountResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(openWithdrawListCountResponseWeb, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "getopenwithdrawlist")
    private ResponseEntity<ResponseToWeb> getOpenWithdrawList(@RequestBody OpenWithdrawListRequest openWithdrawListRequest){
        OpenWithdrawListResponseWeb openWithdrawListResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(openWithdrawListResponseWeb, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "getclosedwithdrawlist/getcount")
    private ResponseEntity<ResponseToWeb> getClosedWithdrawListCount(@RequestBody ClosedWithdrawListRequest closedWithdrawListRequest){
        ClosedWithdrawListCountResponseWeb closedWithdrawListCountResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(closedWithdrawListCountResponseWeb, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "getclosedwithdrawlist")
    private ResponseEntity<ResponseToWeb> getClosedWithdrawList(@RequestBody ClosedWithdrawListRequest closedWithdrawListRequest){
        ClosedWithdrawListResponseWeb closedWithdrawListResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(closedWithdrawListResponseWeb, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "approve")
    private ResponseEntity<ResponseToWeb> approveTransaction(@RequestBody ApproveTransactionRequest approveTransactionRequest){
        ApproveTransactionResponseWeb approveTransactionResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(approveTransactionResponseWeb, responseHeaders, HttpStatus.OK);
    }





}
