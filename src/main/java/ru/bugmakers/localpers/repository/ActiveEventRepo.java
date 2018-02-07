package ru.bugmakers.localpers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.localpers.entity.ActiveEvent;

/**
 * Created by Ivan
 */
public interface ActiveEventRepo extends JpaRepository<ActiveEvent, Long>{

}
