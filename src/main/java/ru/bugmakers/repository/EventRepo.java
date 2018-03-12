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
            "select min (e.startDate) " +
            "from Event e " +
            "where e.user.id = :id")
    LocalDateTime getFirstStartDate(@Param("id") Long userId);

    /**
     * Дата последнего выступления
     *
     * @param userId идентификатор пользователя
     * @return дата и время окончания последнего выступления
     */
    @Query("" +
            "select max (e.endDate) " +
            "from Event e " +
            "where e.user.id = :id")
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

    @Query("" +
            "select " +
            "sum (e.eventDuration) " +
            "from Event e " +
            "where e.user.id = :id " +
            "and e.startDate between :start and :finish")
    Long getAllEventDurationForPeriod(@Param("id") Long userId,
                                      @Param("start") LocalDateTime start,
                                      @Param("finish") LocalDateTime finish);

}
