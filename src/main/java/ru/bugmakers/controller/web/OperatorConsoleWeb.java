package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.ApproveTransactionRequestWeb;
import ru.bugmakers.dto.request.web.ClosedWithdrawListRequestWeb;
import ru.bugmakers.dto.request.web.DispuedWithdrawListWeb;
import ru.bugmakers.dto.request.web.OpenWithdrawListRequestWeb;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.dto.response.web.TransactionListWebRs;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.enums.Status;
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
    private ResponseEntity<MbResponse> getOpenWithdrawList(@RequestBody OpenWithdrawListRequestWeb openWithdrawListRequestWeb) {
        TransactionListWebRs rs;
        try {
            if (openWithdrawListRequestWeb.getPage() != null && openWithdrawListRequestWeb.getSize() != null) {
                rs = getTransactionList(openWithdrawListRequestWeb.getPage(), openWithdrawListRequestWeb.getSize(), Status.OPEN);
            } else {
                throw MbException.create(MbError.CME05);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

    @PostMapping(value = "/getclosedwithdrawlist")
    private ResponseEntity<MbResponse> getClosedWithdrawList(@RequestBody ClosedWithdrawListRequestWeb closedWithdrawListRequestWeb) {
        TransactionListWebRs rs;
        try {
            if (closedWithdrawListRequestWeb.getPage() != null && closedWithdrawListRequestWeb.getSize() != null) {
                rs = getTransactionList(closedWithdrawListRequestWeb.getPage(), closedWithdrawListRequestWeb.getSize(), Status.ACCEPTED);
            } else {
                throw MbException.create(MbError.CME05);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

    @PostMapping(value = "/getdisputedwithdrawlist")
    private ResponseEntity<MbResponse> getDisputedWithdrawList(@RequestBody DispuedWithdrawListWeb dispuedWithdrawListWeb) {
        TransactionListWebRs rs;
        try {
            if (dispuedWithdrawListWeb.getPage() != null && dispuedWithdrawListWeb.getSize() != null) {
                rs = getTransactionList(dispuedWithdrawListWeb.getPage(), dispuedWithdrawListWeb.getSize(), Status.DISPUTED);
            } else {
                throw MbException.create(MbError.CME05);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

    private TransactionListWebRs getTransactionList(String page, String size, Status status) {
        Page<Transaction> transactions = operatorService.getOpenWithdrawList(page, size, status);
        TransactionListWebRs rs = new TransactionListWebRs();
        rs.setTransactions(transactions.getContent());
        rs.setPage(transactions.getNumber() + 1);
        rs.setPageSize(transactions.getSize());
        rs.setTransactionCountInPage(transactions.getNumberOfElements());
        rs.setTotalPages(transactions.getTotalPages());
        rs.setTotalTransaction(transactions.getTotalElements());
        return rs;
    }

    @PostMapping(value = "/make")
    private ResponseEntity<MbResponse> makeTransaction(@RequestBody ApproveTransactionRequestWeb approveTransactionRequestWeb) {
        try {
            if (approveTransactionRequestWeb.getTransactionRequest() != null &&
                    approveTransactionRequestWeb.getTransactionRequest().getTransactionId() != null) {
                operatorService.makeTransaction(approveTransactionRequestWeb.getTransactionRequest().getTransactionId(),
                        approveTransactionRequestWeb.getTransactionRequest().getTransactionStatus());
            } else {
                throw MbException.create(MbError.CME05);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }


}
