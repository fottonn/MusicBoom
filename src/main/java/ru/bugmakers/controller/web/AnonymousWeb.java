package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.request.web.TransactionWebRq;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.TransactionService;
import ru.bugmakers.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/transaction")
public class AnonymousWeb {

    private TransactionService transactionService;
    private UserService userService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<MbResponse> transaction(@RequestBody TransactionWebRq rq) {
        MbResponse rs;
        try {
            if (!userService.isExistsById(Long.valueOf(rq.getRecipientId()))) {
                throw MbException.create(MbError.CME08);
            }
            Transaction transaction = new Transaction();
            transaction.setRecipientId(Long.valueOf(rq.getRecipientId()));
            transaction.setSenderMoneyBearerKind(MoneyBearerKind.CARD);
            transaction.setRecipientMoneyBearerKind(MoneyBearerKind.WALLET);
            transaction.setAmount(new BigDecimal(rq.getSum()));
            transaction.setNumber(rq.getNumberOfTransaction());
            transaction.setDate(LocalDateTime.now());
            transaction.setStatus(Status.ACCEPTED);
            transactionService.saveTransaction(transaction);
            rs = MbResponse.success();
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

}
