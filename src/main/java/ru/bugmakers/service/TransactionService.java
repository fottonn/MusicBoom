package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.repository.TransactionRepo;

import java.time.LocalDateTime;

import static java.math.BigDecimal.ZERO;
import static java.util.Optional.ofNullable;
import static ru.bugmakers.utils.DecimalFormatters.MONEY_FORMATTER;

/**
 * Created by Ivan
 */
@Service
public class TransactionService {

    private TransactionRepo transactionRepo;

    @Autowired
    public void setTransactionRepo(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    /**
     * Сумма зачисленных денег за всё время
     *
     * @param userId идентификатор пользователя
     * @return сумма полученных пользователем денег за всё время в формате ###.##
     */
    public String getAllReceivedMoney(Long userId) {
        return MONEY_FORMATTER.format(ofNullable(transactionRepo.getReceivedMoney(userId)).orElse(ZERO));
    }

    /**
     * Сумма выведенных пользователем денег за всё время
     *
     * @param userId идентификатор пользователя
     * @return сумма выведенных пользователем денег, включая переведенные на кошельки других пользователей,
     * за всё время в формате ###.##
     */
    public String getAllDerivedMoney(Long userId) {
        return MONEY_FORMATTER.format(ofNullable(transactionRepo.getDerivedMoney(userId)).orElse(ZERO));
    }

    /**
     * Текущий баланс пользователя
     *
     * @param userId идентификатор пользователя
     * @return разность между полученными деньгами и выведенными в формате ###.## за всё время
     */
    public String getCurrentBalance(Long userId) {
        return MONEY_FORMATTER.format(ofNullable(transactionRepo.getReceivedMoney(userId)).orElse(ZERO)
                .subtract(ofNullable(transactionRepo.getDerivedMoney(userId)).orElse(ZERO)));
    }

    /**
     * Зачисленные деньги за период
     *
     * @param userId идентификатор пользователя
     * @param start  начало периода
     * @param finish конец периода
     * @return полученные деньги за период в формате ###.##
     */
    public String getReceivedMoneyForPeriod(Long userId, LocalDateTime start, LocalDateTime finish) {
        return MONEY_FORMATTER.format(ofNullable(transactionRepo.getReceivedMoneyForPeriod(userId, start, finish)).orElse(ZERO));
    }

    /**
     * Выведенные деньги за период
     *
     * @param userId идентификатор пользователя
     * @param start  начало периода
     * @param finish конец периода
     * @return выведенные деньги, включая переведенные на кошельки других пользователей, за период в формате ###.##
     */
    public String getDerivedMoneyForPeriod(Long userId, LocalDateTime start, LocalDateTime finish) {
        return MONEY_FORMATTER.format(ofNullable(transactionRepo.getDerivedMoneyForPeriod(userId, start, finish)).orElse(ZERO));
    }

    /**
     * Общее количество артистов, которым перечислял денежные средства пользователь
     *
     * @param userId идентификатор пользователя
     * @return количество всех артистов, которым задонатил пользователь
     */
    public String allDonatedArtistCount(Long userId) {
        return String.valueOf(transactionRepo.countDistinctBySenderIdAndRecipientMoneyBearerKind(userId, MoneyBearerKind.WALLET));
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepo.saveAndFlush(transaction);
    }
}
