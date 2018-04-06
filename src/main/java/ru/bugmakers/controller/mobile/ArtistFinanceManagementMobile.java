package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.ArtistWithdrawMobileRq;
import ru.bugmakers.dto.request.mobile.CardInfoRequestMobile;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.ArtistFinanceManagementService;

/**
 * Контроллер для управления финансами через МП
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist")
public class ArtistFinanceManagementMobile extends MbController {

    private ArtistFinanceManagementService artistFinanceManagementService;

    @Autowired
    public void setArtistFinanceManagementService(ArtistFinanceManagementService artistFinanceManagementService) {
        this.artistFinanceManagementService = artistFinanceManagementService;
    }

    /**
     * Привязывание карточки куда будут выводиться деньги
     *
     * @param user                  - пользователь
     * @param cardInfoRequestMobile - номер карточки
     */
    @PostMapping(value = "/card.attach")
    public ResponseEntity<MbResponse> cardAttach(@AuthenticationPrincipal UserPrincipal user,
                                                 @RequestBody CardInfoRequestMobile cardInfoRequestMobile) {
        try {
            if (cardInfoRequestMobile.getCardNumber() == null) {
                throw MbException.create(MbError.FDE01);
            }
            artistFinanceManagementService.attachCard(cardInfoRequestMobile.getCardNumber(), user.getUser());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());

    }

    /**
     * Обновление карточки
     *
     * @param user                  - пользователь
     * @param cardInfoRequestMobile - номер карточки
     */
    @PostMapping(value = "/card.update")
    public ResponseEntity<MbResponse> cardUpdate(@AuthenticationPrincipal UserPrincipal user,
                                                 @RequestBody CardInfoRequestMobile cardInfoRequestMobile) {
        return this.cardAttach(user, cardInfoRequestMobile);
    }

    /**
     * Удаление привязанно карточки
     *
     * @param user - пользователь
     * @return - ответ на ПЛ
     */
    @PostMapping(value = "/card.detach")
    public ResponseEntity<MbResponse> cardDetach(@AuthenticationPrincipal UserPrincipal user) {
        try {
            artistFinanceManagementService.detachCard(user.getUser());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }

    /**
     * Вывод денег пользователя на карту
     *
     * @param user - пользователь
     * @return - ответ на ПЛ
     */
    @PostMapping(value = "/withdraw")
    public ResponseEntity<MbResponse> withdraw(@AuthenticationPrincipal UserPrincipal user,
                                               @RequestBody ArtistWithdrawMobileRq rq) {
        try {
            artistFinanceManagementService.withdraw(user.getUser(), rq.getSum());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }
}
