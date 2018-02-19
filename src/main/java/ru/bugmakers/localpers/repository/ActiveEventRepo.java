package ru.bugmakers.localpers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.localpers.entity.ActiveEvent;

import java.util.List;

/**
 * Created by Ivan
 */
public interface ActiveEventRepo extends JpaRepository<ActiveEvent, Long> {

    List<ActiveEvent> findByLngBetweenAndLatBetweenOrderByUserId(Double lngMin, Double lngMax, Double latMin, Double latMax);

}
