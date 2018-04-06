package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.mobile.GetArtistRqMobile;
import ru.bugmakers.dto.request.mobile.TransactionRequestMobile;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.dto.response.mobile.GetArtistResponseMobile;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;
import ru.bugmakers.service.ArtistInfoPageService;
import ru.bugmakers.service.TransactionService;
import ru.bugmakers.utils.DateTimeFormatters;

import java.math.BigDecimal;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi")
public class ArtistInfoPageMobile extends MbController {

    private ArtistInfoPageService artistInfoPageService;
    private TransactionService transactionService;

    @Autowired
    public void setArtistInfoPageService(ArtistInfoPageService artistInfoPageService) {
        this.artistInfoPageService = artistInfoPageService;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/artist.get")
    public ResponseEntity<MbResponse> getArtist(@RequestBody GetArtistRqMobile rq) {
        GetArtistResponseMobile rs;
        try {
            UserDTO userDTO = artistInfoPageService.getArtistForOtherUsers(Long.valueOf(rq.getArtistId()));
            rs = new GetArtistResponseMobile();
            rs.setUserDTO(userDTO);
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

    @PostMapping(value = "/transaction")
    public ResponseEntity<MbResponse> transaction(@RequestBody TransactionRequestMobile rq) {

        MbResponse rs;
        try {
            Transaction transaction = new Transaction();
            transaction.setSenderId(Long.valueOf(rq.getSenderId()));
            transaction.setRecipientId(Long.valueOf(rq.getRecipientId()));
            transaction.setSenderMoneyBearerKind(MoneyBearerKind.CARD);
            transaction.setRecipientMoneyBearerKind(MoneyBearerKind.WALLET);
            transaction.setAmount(new BigDecimal(rq.getSum()));
            transaction.setNumber(rq.getNumberOfTransaction());
            transaction.setDate(DateTimeFormatters.parseLocalDateTime(rq.getDate()));
            transaction.setStatus(Status.ACCEPTED);
            transactionService.saveTransaction(transaction);
            rs = MbResponse.success();
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }

        return ResponseEntity.ok(rs);
    }

}

