package ru.bugmakers.service;

import org.cfg4j.provider.ConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.repository.TransactionRepo;
import ru.bugmakers.utils.BigDecimalUtils;

import java.math.BigDecimal;
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
    private ConfigurationProvider appConfigProvider;

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

    @Autowired
    @Qualifier("appConfigProvider")
    public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
        this.appConfigProvider = appConfigProvider;
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

    /**
     * Сохранение транзакции в БД
     * <p>
     * Если получатель денежных средств АРТИСТ, и перевод осуществляется на внутренний кошелек с других платежных
     * средств за исключением внутреннего кошелька, то вычитаем комиссию, установленную текущему артисту и, если этот
     * артист является рефералом другого артиста, перечисляем рефереру бонус по реферальной программе.
     *
     * @param transaction транзакция
     */
    @Transactional
    public void saveTransaction(Transaction transaction) {
        User recipient = userService.findUserById(transaction.getRecipientId());
        if (recipient.getUserType() == UserType.ARTIST
                && transaction.getRecipientMoneyBearerKind() == MoneyBearerKind.WALLET
                && transaction.getSenderMoneyBearerKind() == MoneyBearerKind.CARD) {
            final BigDecimal fee = rankPropsService.getFeeByRank(recipient.getRank());
            BigDecimal amount = new BigDecimal(transaction.getAmount().toString());
            transaction.setAmount(BigDecimalUtils.withoutFee(amount, fee));
            transaction.setFee(BigDecimalUtils.fee(amount, fee));
            transaction.setProfit(BigDecimalUtils.profit(
                    amount,
                    transaction.getFee(),
                    appConfigProvider.getProperty("payment.system.fee", BigDecimal.class)));
            //если у артиста есть реферер и срок реферальной программы не истёк
            if (recipient.getReferrer() != null && recipient.getRegistrationDate()
                    .plusSeconds(appConfigProvider.getProperty("referral.program.duration", Long.class))
                    .isAfter(LocalDateTime.now())) {
                BigDecimal referrerBonus = BigDecimalUtils.referrerBonus(
                        transaction.getAmount(),
                        BigDecimal.valueOf(appConfigProvider.getProperty("referral.program.bonus", long.class)));
                transaction.setReferrerBonus(referrerBonus);
                transaction.setProfit(transaction.getProfit().subtract(referrerBonus));
                Transaction referrerTransaction = new Transaction();
                referrerTransaction.setAmount(referrerBonus);
                referrerTransaction.setRecipientId(recipient.getReferrer().getId());
                referrerTransaction.setSenderId(recipient.getId());
                referrerTransaction.setRecipientMoneyBearerKind(MoneyBearerKind.WALLET);
                referrerTransaction.setSenderMoneyBearerKind(MoneyBearerKind.REFERRAL);
                referrerTransaction.setStatus(Status.ACCEPTED);
                saveTransaction(referrerTransaction);
            }
        }
        transactionRepo.saveAndFlush(transaction);
    }

    /**
     * Метод поиска транзакии по ID
     *
     * @param transactionId - id транзакции
     * @return объект транзакции
     */
    public Transaction findTransactionById(String transactionId) {
        return transactionRepo.findById(transactionId).orElse(null);
    }

    /**
     * Метод который ищет все транзакции с определенным статусом
     *
     * @param status   - статус транзакции {@link Status}
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
