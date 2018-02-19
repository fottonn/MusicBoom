package ru.bugmakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.entity.FeedBack;

/**
 * Created by Ivan
 */
public interface FeedBackRepo extends JpaRepository<FeedBack, Long> {



}
