package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.CardInfoRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistEditingResponseMobile;
import ru.bugmakers.dto.response.mobile.FinanceManagementResponseMobile;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.ArtistFinanceManagementService;

/**
 * Контроллер для управления финансами через МП
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/")
public class ArtistFinanceManagementMobile extends MbController {

    private ArtistFinanceManagementService artistFinanceManagementService;

    @Autowired
    public void setArtistFinanceManagementService(ArtistFinanceManagementService artistFinanceManagementService) {
        this.artistFinanceManagementService = artistFinanceManagementService;
    }

    /**
     * Привязывание карточки куда будут выводиться деньги
     * @param user                  - пользователь
     * @param cardInfoRequestMobile - номер карточки
     * @return
     */
    @PostMapping(value = "card.attach")
    public ResponseEntity<MbResponseToMobile> cardAttach(@AuthenticationPrincipal UserPrincipal user,
                                                         @RequestBody CardInfoRequestMobile cardInfoRequestMobile) {
        try {
            if (cardInfoRequestMobile.getCardNumber() == null) {
                throw MbException.create(MbError.FDE01);
            }
            artistFinanceManagementService.attachCard(cardInfoRequestMobile.getCardNumber(), user.getUser());
        } catch (MbException e) {
            return ResponseEntity.ok(new FinanceManagementResponseMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new FinanceManagementResponseMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));

    }

    /**
     * Обновление карточки
     * @param user                  - пользователь
     * @param cardInfoRequestMobile - номер карточки
     */
    @PostMapping(value = "card.update")
    public void cardUpdate(@AuthenticationPrincipal UserPrincipal user,
                           @RequestBody CardInfoRequestMobile cardInfoRequestMobile) {
        this.cardAttach(user, cardInfoRequestMobile);
    }

    /**
     * Удаление привязанно карточки
     * @param user  - пользователь
     * @return      - ответ на ПЛ
     */
    @PostMapping(value = "card.detach")
    public ResponseEntity<MbResponseToMobile> cardDetach(@AuthenticationPrincipal UserPrincipal user
                                                         ) {
        try {
            artistFinanceManagementService.detachCard(user.getUser());
        } catch (MbException e) {
            return ResponseEntity.ok(new FinanceManagementResponseMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new FinanceManagementResponseMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));
    }

    /**
     * Вывод денег пользователя на карту
     * @param user      - пользователь
     * @param amount    - сумма
     * @return          - ответ на ПЛ
     */
    @PostMapping(value = "withdraw")
    public ResponseEntity<MbResponseToMobile> withdraw(@AuthenticationPrincipal UserPrincipal user,
                                                       @RequestParam("sum") String amount) {
        try {
            artistFinanceManagementService.withdraw(user.getUser(), amount);
        } catch (MbException e) {
            return ResponseEntity.ok(new FinanceManagementResponseMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new FinanceManagementResponseMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));
    }
}
