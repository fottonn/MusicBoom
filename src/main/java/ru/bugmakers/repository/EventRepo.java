package ru.bugmakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.bugmakers.entity.Event;

import java.time.LocalDateTime;

/**
 * Created by Ivan
 */
public interface EventRepo extends JpaRepository<Event, Long> {

    /**
     * Количество всех выступлений артисти
     *
     * @param id идентификатор пользователя
     * @return количество всех выступлений
     */
    int countByUserId(Long id);

    /**
     * Дата первого выступления
     *
     * @param userId идентификатор пользователя
     * @return дата и время начала первого выступления
     */
    @Query("" +
            "select e.startDate " +
            "from Event e " +
            "where e.user.id = :id " +
            "and e.startDate = min(e.startDate)")
    LocalDateTime getFirstStartDate(@Param("id") Long userId);

    /**
     * Дата последнего выступления
     *
     * @param userId идентификатор пользователя
     * @return дата и время окончания последнего выступления
     */
    @Query("" +
            "select e.endDate " +
            "from Event e " +
            "where e.user.id = :id " +
            "and e.endDate = max(e.endDate)")
    LocalDateTime getLastEndDate(@Param("id") Long userId);

    /**
     * Длительность всех выступлений
     *
     * @param userId идентификатор пользователя
     * @return длительность всех выступлений в минутах
     */
    @Query("" +
            "select " +
            "sum (e.eventDuration) " +
            "from Event e " +
            "where e.user.id = :id")
    Long getAllEventDuration(@Param("id") Long userId);

}
