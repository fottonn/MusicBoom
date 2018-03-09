package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.request.mobile.CardInfoRequestMobile;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.utils.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Ayrat on 19.02.2018.
 * Сервис по управлению финансовыми данными артиста через МП
 */
@Component
public class ArtistFinanceManagementService  {
    private UserService userService;
    private TransactionService transactionService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Метод который позволяет пользователю прикрепить карту.
     * @param id - id пользователя
     * @param cardInfoRequestMobile - информация о карте
     * @return
     * @throws MbException
     */
    public Boolean attachCard(String id, CardInfoRequestMobile cardInfoRequestMobile) throws MbException {
        User userById = userService.findUserById(Long.valueOf(id));
        if (userById == null) {
            throw MbException.create(MbError.CME03);
        }
        userById.setCardNumber(cardInfoRequestMobile.getCardNumber());
        User user = userService.saveUser(userById);
        if (user == null) {
            throw MbException.create(MbError.CME04);
        }
        return Boolean.TRUE;
    }

    /**
     * Метод который позволяет открепить карту.
     * @param id - id пользовталя
     * @return
     * @throws MbException
     */
    public Boolean detachCard(String id) throws MbException {
        User userById = userService.findUserById(Long.valueOf(id));
        if (userById == null) {
            throw MbException.create(MbError.CME03);
        }
        userById.setCardNumber(null);
        User user = userService.saveUser(userById);
        if (user == null) {
            throw MbException.create(MbError.CME04);
        }
        return Boolean.TRUE;
    }

    /**
     * Сервис который позволяет запланировать вывод пользователем средств себе на карту
     * @param id - id пользователя
     * @param amount - выводимая им сумма
     * @return
     * @throws MbException
     */
    public void withdraw(String id, String amount) throws MbException {
        User userById = userService.findUserById(Long.valueOf(id));
        if (userById == null) {
            throw MbException.create(MbError.CME03);
        }
        if (amountValidation(amount, userById)) {
            throw MbException.create(MbError.TRE01);
        }
        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setNumber(UuidGenerator.timeBasedUuidGenerate());
        transaction.setRecipient(userById);
        transaction.setSender(userById);
        transaction.setAmount(new BigDecimal(amount));
        transaction.setStatus(Status.OPEN);
        transaction.setSenderMoneyBearerKind(MoneyBearerKind.WALLET);
        transaction.setRecipientMoneyBearerKind(MoneyBearerKind.CARD);
        transactionService.saveTransaction(transaction);
    }

    /**
     * Проверяем доступна ли указанная сумма пользователю для вывода
     * @param amount - суммма
     * @param user - пользователь
     * @return если сумма равна или меньше доступной у пользователя то FALSE иначе TRUE.
     */
    private Boolean amountValidation(String amount, User user) {
        BigDecimal userCurrentBalance = new BigDecimal(transactionService.getCurrentBalance(user.getId()));
        BigDecimal withdrawAmount = new BigDecimal(amount);
        return (userCurrentBalance.compareTo(withdrawAmount) == 1) || (userCurrentBalance.compareTo(withdrawAmount) == 0) ? Boolean.FALSE : Boolean.TRUE;
    }
}
