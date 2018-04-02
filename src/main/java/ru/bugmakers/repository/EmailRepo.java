package ru.bugmakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.entity.Email;

/**
 * Created by Ivan
 */
public interface EmailRepo extends JpaRepository<Email, Long> {

    Email findByConfirmationCode(String code);

}
