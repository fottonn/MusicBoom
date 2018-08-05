package ru.bugmakers.localpers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.bugmakers.localpers.entity.ActiveEvent;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Ivan
 */
public interface ActiveEventRepo extends JpaRepository<ActiveEvent, Long> {

    List<ActiveEvent> findByLngBetweenAndLatBetweenOrderByUserId(Double lngMin, Double lngMax, Double latMin, Double latMax);

    /**
     * Поиск активных выступлений, у которых время последнего обновления не превышает переданного времени
     *
     * @param minTime минимальное допустимое время последнего обновления
     * @return список активных выступлений
     */
    @Query("select ae from ActiveEvent ae where ae.lastUpdate < :time")
    List<ActiveEvent> findAllOverdueEvents(@Param("time") LocalDateTime minTime);

}
