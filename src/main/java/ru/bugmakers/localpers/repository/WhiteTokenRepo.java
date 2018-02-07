package ru.bugmakers.localpers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.localpers.entity.WhiteToken;

/**
 * Created by Ivan
 */
public interface WhiteTokenRepo extends JpaRepository<WhiteToken, Long> {

}
