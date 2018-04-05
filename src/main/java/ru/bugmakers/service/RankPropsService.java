package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.enums.Rank;
import ru.bugmakers.repository.RankPropsRepo;

import java.math.BigDecimal;

/**
 * Created by Ivan
 */
@Service
public class RankPropsService {

    private RankPropsRepo rankPropsRepo;

    @Autowired
    public void setRankPropsRepo(RankPropsRepo rankPropsRepo) {
        this.rankPropsRepo = rankPropsRepo;
    }

    public BigDecimal getFeeByRank(Rank rank) {
        if (rank == null) rank = Rank.NEWBE;
        return rankPropsRepo.findByRank(rank).getFee();
    }
}
