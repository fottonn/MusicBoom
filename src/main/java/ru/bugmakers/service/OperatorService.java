package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.enums.MoneyBearerKind;
import ru.bugmakers.enums.Status;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

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
     * Установка статуса транзакции
     *
     * @param transactionId     идентификатор транзакции
     * @param transactionStatus статус транзакции
     */
    public void makeTransaction(String transactionId, String transactionStatus) throws MbException {
        Transaction transactionById = transactionService.findTransactionById(transactionId);
        if (transactionById == null) throw MbException.create(MbError.TRE02);
        checkWithdrawTransaction(transactionById);
        Status status;
        try {
            status = Status.valueOf(transactionStatus);
        } catch (IllegalArgumentException e) {
            throw MbException.create(MbError.TRE03);
        }
        transactionById.setStatus(status);
        transactionService.saveTransaction(transactionById);
    }

    /**
     * Проверка транзакции вывода денежных средств пользователем
     * Направление транзакции должно быть с внутреннего кошелька на карту.
     *
     * @param transaction - транзакция
     */
    private void checkWithdrawTransaction(Transaction transaction) throws MbException {
        if (!transaction.getRecipientMoneyBearerKind().equals(MoneyBearerKind.CARD)
                && !transaction.getSenderMoneyBearerKind().equals(MoneyBearerKind.WALLET)) {
            throw MbException.create(MbError.TRE06);
        }

    }

    /**
     * Метод который возвращает список всех транзакий которые соответствую статусу {@link Status}
     *
     * @param page   - номер запрашиваемой страницы
     * @param size   - количество элементов на странице
     * @param status - статус транзакций
     * @return страница с транзакциями
     */
    public Page<Transaction> getOpenWithdrawList(String page, String size, Status status) {
        int localPage = Integer.parseInt(page) - 1;
        int localSize = Integer.parseInt(size);
        return transactionService.findAllTransactionByStatus(status, PageRequest.of(localPage, localSize, Sort.by("id")));
    }
}
