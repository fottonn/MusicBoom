package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.response.web.TransactionListWebRs;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.enums.Status;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

import java.time.LocalDateTime;

/**
 * Сервия предназначен для кабинета оператора,
 * в котором поисходят основные операции с транзакциями
 */
@Component
public class OperatorService {
    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Сервис который позволяет проводить или отклонять транзакции
     * @param transactionId - ID транзакции
     * @param transactionStatus
     */
    public void makeTransaction(String transactionId, String transactionStatus) throws MbException {
        Transaction transactionById = transactionService.findTransactionById(transactionId);
        if (checkTransactionType(transactionById)) {
            Status status;
            if (transactionById != null) {
                try {
                    status = Status.valueOf(transactionStatus);
                }
                catch (IllegalArgumentException e){
                    throw MbException.create(MbError.TRE03);
                }
                transactionById.setStatus(status);
                transactionById.setDate(LocalDateTime.now());
                transactionService.saveTransaction(transactionById);
            }else{
                throw MbException.create(MbError.TRE02);
            }
        }
    }

    /**
     * Метод для проверки типа транзакции, направление транзакции должно быть с внутреннего кошелька
     * и направлено не на внутренний кошелек.
     * @param transactionById - транзакция
     * @return
     */
    private Boolean checkTransactionType(Transaction transactionById) {
        if (transactionById.getRecipientMoneyBearerKind().equals(MoneyBearerKind.WALLET) && !
                transactionById.getSenderMoneyBearerKind().equals(MoneyBearerKind.WALLET)) {
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }

    }

    /**
     * Метод который возвращает списко всех транзакий которые соответствую статусу {@link Status}
     * @param page - номер запрашиваемой страницы
     * @param size - количество элементов на странице
     * @param status - статут транзакций
     * @return
     */
    public TransactionListWebRs getOpenWithdrawList(String page, String size, Status status) {
        int localPage = Integer.parseInt(page) - 1;
        int localSize = Integer.parseInt(size);
        TransactionListWebRs rs = new TransactionListWebRs(RsStatus.SUCCESS);
        Page<Transaction> transactions = transactionService.findAllTransactionByStatus(status, PageRequest.of(localPage, localSize, Sort.by("id")));
        rs.setTransactions(transactions.getContent());
        rs.setPage(transactions.getNumber() + 1);
        rs.setPageSize(transactions.getSize());
        rs.setTransactionCountInPage(transactions.getNumberOfElements());
        rs.setTotalPages(transactions.getTotalPages());
        rs.setTotalTransaction(transactions.getTotalElements());
        return rs;
    }
}
