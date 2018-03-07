package ru.bugmakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bugmakers.entity.RankProps;
import ru.bugmakers.enums.Rank;

/**
 * Created by Ivan
 */
@Repository
public interface RankPropsRepo extends JpaRepository<RankProps, Long> {

    RankProps findByRank(Rank rank);

}
