package ru.bugmakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.enums.MoneyBearerKind;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Ivan
 */
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    /**
     * @param id идентификатор пользователя
     * @return зачисленные деньги на внутренний кошелек за всё время
     */
    @Query("" +
            "select " +
            "sum (t.amount) " +
            "from Transaction t " +
            "where t.recipientId = :id")
    BigDecimal getReceivedMoney(@Param("id") Long id);

    /**
     * @param id идентификатор пользователя
     * @return выведенные деньги с внутреннего кошелька за всё время, включая внутренние переводы на кошельки других пользователей
     */
    @Query("" +
            "select " +
            "sum (t.amount) " +
            "from Transaction t " +
            "where t.senderId = :id " +
            "and t.senderMoneyBearerKind = ru.bugmakers.enums.MoneyBearerKind.WALLET")
    BigDecimal getDerivedMoney(@Param("id") Long id);

    /**
     * @param id     идентификатор пользователя
     * @param start  начало периода
     * @param finish конец периода
     * @return зачисленные деньги на внутренний кошелек за период
     */
    @Query("" +
            "select " +
            "sum (t.amount) " +
            "from Transaction t " +
            "where t.recipientId = :id " +
            "and t.date between :start and :finish")
    BigDecimal getReceivedMoneyForPeriod(@Param("id") Long id, @Param("start") LocalDateTime start, @Param("finish") LocalDateTime finish);

    /**
     * @param id     идентификатор пользователя
     * @param start  начало периода
     * @param finish конец периода
     * @return выведенные деньги с внутреннего кошелька за период, включая внутренние переводы на кошельки других пользователей
     */
    @Query("" +
            "select " +
            "sum (t.amount) " +
            "from Transaction t " +
            "where t.senderId = :id " +
            "and t.senderMoneyBearerKind = ru.bugmakers.enums.MoneyBearerKind.WALLET " +
            "and t.date between :start and :finish")
    BigDecimal getDerivedMoneyForPeriod(@Param("id") Long id, @Param("start") LocalDateTime start, @Param("finish") LocalDateTime finish);

    /**
     * @param id                       идентификатор пользователя отправителя денежных средств
     * @param recipientMoneyBearerKind тип носителя денежных средств получателя
     * @return общее количество артистов, которым пользователь с {@code id} перечислял денежные средства c типом
     * носителя денежных средств {@code recipientMoneyBearerKind}
     */
    int countDistinctBySenderIdAndRecipientMoneyBearerKind(Long id, MoneyBearerKind recipientMoneyBearerKind);

}
