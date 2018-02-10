package ru.bugmakers.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.entity.User;
import ru.bugmakers.repository.TransactionRepo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

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
     * Сумма заработанных артистом денег за период
     *
     * @param artist артист
     * @param start  начало периода
     * @param end    конец периода
     * @return сумма полученных денег за период
     */
    public BigDecimal getReceivedMoneyForPeriod(User artist, LocalDateTime start, LocalDateTime end) {
        List<Transaction> receivedTransactions = transactionRepo.findByRecipientAndDateBetween(artist, start, end);
        BigDecimal receivedMoney = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(receivedTransactions)) {
            for (Transaction t : receivedTransactions) {
                receivedMoney = receivedMoney.add(t.getAmount());
            }
        }
        return receivedMoney;
    }

    /**
     * Сумма заработанных артистом денег за период в String
     *
     * @see TransactionService#getReceivedMoneyForPeriod(User, LocalDateTime, LocalDateTime)
     * @return сумма полученных денег за период в формате "###.##"
     */
    public String getReceivedMoneyForPeriodString(User artist, LocalDateTime start, LocalDateTime end) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        df.setRoundingMode(RoundingMode.DOWN);
        df.setGroupingUsed(false);
        return df.format(getReceivedMoneyForPeriod(artist, start, end)).replaceAll(",", ".");
    }
}
