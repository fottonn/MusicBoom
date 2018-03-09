package ru.bugmakers.entity;

import ru.bugmakers.enums.Rank;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Ivan
 */
@Entity
@Table(name = "rank_props")
public class RankProps {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "rank")
    @Enumerated(EnumType.STRING)
    private Rank rank;

    /**
     * Комиссия в %
     */
    @Column(name = "fee")
    private BigDecimal fee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
