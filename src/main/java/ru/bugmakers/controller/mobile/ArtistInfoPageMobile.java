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
import ru.bugmakers.dto.response.mobile.GetArtistResponseMobile;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.enums.Status;
import ru.bugmakers.exceptions.MbException;
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
    public ResponseEntity<MbResponseToMobile> getArtist(@RequestBody GetArtistRqMobile rq) {
        GetArtistResponseMobile getArtistResponseMobile;
        try {
            UserDTO userDTO = artistInfoPageService.getArtistForOtherUsers(Long.valueOf(rq.getArtistId()));
            getArtistResponseMobile = new GetArtistResponseMobile(RsStatus.SUCCESS);
            getArtistResponseMobile.setUserDTO(userDTO);
        } catch (MbException e) {
            getArtistResponseMobile = new GetArtistResponseMobile(e, RsStatus.ERROR);
        } catch (Exception e) {
            getArtistResponseMobile = new GetArtistResponseMobile(RsStatus.ERROR);
        }
        return ResponseEntity.ok(getArtistResponseMobile);
    }

    @PostMapping(value = "/transaction")
    public ResponseEntity<MbResponseToMobile> transaction(@RequestBody TransactionRequestMobile rq) {

        MbResponseToMobile rs;
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
            rs = new MbResponseToMobile(RsStatus.SUCCESS);
        } catch (Exception e) {
            rs = new MbResponseToMobile(RsStatus.ERROR);
        }

        return ResponseEntity.ok(rs);
    }

}

