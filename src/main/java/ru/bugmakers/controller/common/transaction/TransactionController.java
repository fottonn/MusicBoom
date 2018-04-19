package ru.bugmakers.controller.common.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.TransactionService;
import ru.bugmakers.service.UserService;
import ru.bugmakers.validator.TransactionRequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static ru.bugmakers.validator.TransactionRequestValidator.*;

/**
 * Created by Ivan
 */

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionService transactionService;
    private UserService userService;
    private TransactionRequestValidator transactionRequestValidator;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTransactionRequestValidator(TransactionRequestValidator transactionRequestValidator) {
        this.transactionRequestValidator = transactionRequestValidator;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity transaction(@RequestParam Map<String, String> map) {

        try {

            transactionRequestValidator.validate(map);
            Long recipientId = Long.valueOf(map.get(LABEL));
            if (!userService.isExistsById(recipientId)) {
                throw MbException.create(MbError.CME08);
            }

            String operationId = map.get(OPERATION_ID);
            if (!transactionService.isExistsByTransactionNumber(operationId)) {
                Transaction transaction = new Transaction();
                transaction.setRecipientId(recipientId);
                transaction.setSenderMoneyBearerKind(MoneyBearerKind.CARD);
                transaction.setRecipientMoneyBearerKind(MoneyBearerKind.WALLET);
                transaction.setAmount(new BigDecimal(map.get(AMOUNT)));
                transaction.setNumber(operationId);
                transaction.setDate(LocalDateTime.now());
                transaction.setStatus(Status.ACCEPTED);
                transactionService.saveTransaction(transaction);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }


}
