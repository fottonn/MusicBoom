package ru.bugmakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.entity.Transaction;
import ru.bugmakers.entity.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Ivan
 */
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySenderAndDateBetween(User sender, LocalDateTime start, LocalDateTime end);

    List<Transaction> findByRecipientAndDateBetween(User recipient, LocalDateTime start, LocalDateTime end);

}
