package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.repository.TransactionRepo;
import ru.bugmakers.utils.BigDecimalUtils;

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
    private RankPropsService rankPropsService;
    private UserService userService;

    @Autowired
    public void setTransactionRepo(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Autowired
    public void setRankPropsService(RankPropsService rankPropsService) {
        this.rankPropsService = rankPropsService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
    public String  getAllDerivedMoney(Long userId) {
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

    /**
     * Сохранение транзакции в БД
     *
     * Если получатель денежных средств АРТИСТ, и перевод осуществляется на внутренний кошелек с других платежных
     * средств за исключением внутреннего кошелька, то вычитаем комиссию, установленную текущему артисту
     *
     * @param transaction транзакция
     */
    public void saveTransaction(Transaction transaction) {
        User recipient = userService.findUserById(transaction.getRecipientId());
        if (recipient.getUserType() == UserType.ARTIST
                && transaction.getRecipientMoneyBearerKind() == MoneyBearerKind.WALLET
                && transaction.getSenderMoneyBearerKind() != MoneyBearerKind.WALLET) {
            transaction.setAmount(BigDecimalUtils.withoutFee(transaction.getAmount(),
                    rankPropsService.getFeeByRank(recipient.getRank())));
        }
        transactionRepo.saveAndFlush(transaction);
    }

    /**
     * Метод по поиску транзакии по ID
     * @param transactionId - id транзакции
     * @return
     */
    public Transaction findTransactionById(String transactionId) {
        return transactionRepo.findById(transactionId).orElse(null);
    }

    /**
     * Метод который ищет все транзакции с определенным статусом
     * @param status - статус транзакции {@link Status}
     * @param pageable - информация о странице {@link Pageable}
     * @return - список транзакций
     */
    public Page<Transaction> findAllTransactionByStatus(final Status status, final Pageable pageable) {
        return transactionRepo.findAllByStatus(status, pageable);
    }

    public boolean isExistsByTransactionNumber(String number) {
        return transactionRepo.existsByNumber(number);
    }
}
