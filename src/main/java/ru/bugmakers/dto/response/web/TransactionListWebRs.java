package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.entity.Transaction;

import java.util.List;

public class TransactionListWebRs extends MbResponse {

    private int page;
    private int pageSize;
    private int transactionCountInPage;
    private int totalPages;
    private long totalTransaction;
    private List<Transaction> transactions;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTransactionCountInPage() {
        return transactionCountInPage;
    }

    public void setTransactionCountInPage(int transactionCountInPage) {
        this.transactionCountInPage = transactionCountInPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalTransaction() {
        return totalTransaction;
    }

    public void setTotalTransaction(long totalTransaction) {
        this.totalTransaction = totalTransaction;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
