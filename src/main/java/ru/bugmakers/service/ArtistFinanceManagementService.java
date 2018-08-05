package ru.bugmakers.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.utils.UuidGenerator;

import java.math.BigDecimal;

/**
 * Created by Ayrat on 19.02.2018.
 * Сервис по управлению финансовыми данными артиста через МП
 */
@Component
public class ArtistFinanceManagementService {
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
     *
     * @param cardNumber - номер карты
     * @param user       - пользователь
     */
    public void attachCard(String cardNumber, User user) throws MbException {
        user.setCardNumber(cardNumber);
        user.setLinkedCard(true);
        User savedUser = userService.saveUser(user);
        if (savedUser == null) {
            throw MbException.create(MbError.CME04);
        }
    }

    /**
     * Метод который позволяет открепить карту.
     *
     * @param user - пользователь
     */
    public void detachCard(User user) throws MbException {
        user.setCardNumber(null);
        user.setLinkedCard(false);
        User savedUser = userService.saveUser(user);
        if (savedUser == null) {
            throw MbException.create(MbError.CME04);
        }
    }

    /**
     * Сервис который позволяет запланировать вывод пользователем средств себе на карту
     *
     * @param user   - пользователь
     * @param amount - выводимая им сумма
     */
    public void withdraw(User user, String amount) throws MbException {
        if (!amountValidation(amount, user)) {
            throw MbException.create(MbError.TRE01);
        }
        if (StringUtils.isBlank(user.getCardNumber())) {
            throw MbException.create(MbError.TRE04);
        }
        Transaction transaction = new Transaction();
        transaction.setNumber(UuidGenerator.timeBasedUuidGenerate());
        transaction.setRecipientId(user.getId());
        transaction.setSenderId(user.getId());
        transaction.setAmount(new BigDecimal(amount));
        transaction.setStatus(Status.OPEN);
        transaction.setSenderMoneyBearerKind(MoneyBearerKind.WALLET);
        transaction.setRecipientMoneyBearerKind(MoneyBearerKind.CARD);
        transactionService.saveTransaction(transaction);
    }

    /**
     * Проверяем доступна ли указанная сумма пользователю для вывода
     *
     * @param amount - суммма
     * @param user   - пользователь
     * @return если сумма равна или меньше доступной у пользователя то TRUE иначе FALSE.
     */
    private Boolean amountValidation(String amount, User user) {
        BigDecimal userCurrentBalance = new BigDecimal(transactionService.getCurrentBalance(user.getId()));
        BigDecimal withdrawAmount = new BigDecimal(amount);
        return (userCurrentBalance.compareTo(withdrawAmount) > 0) || (userCurrentBalance.compareTo(withdrawAmount) == 0) ? Boolean.TRUE : Boolean.FALSE;
    }
}
