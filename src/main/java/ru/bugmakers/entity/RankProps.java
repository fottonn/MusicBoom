package ru.bugmakers.entity;

import ru.bugmakers.enums.Rank;

import javax.persistence.*;
import java.math.BigDecimal;

import static ru.bugmakers.entity.EntityConstants.RANK_PROPS_GEN;
import static ru.bugmakers.entity.EntityConstants.RANK_PROPS_SEQ;

/**
 * Created by Ivan
 */
@Entity
@Table(name = "rank_props")
@SequenceGenerator(name = RANK_PROPS_GEN, sequenceName = RANK_PROPS_SEQ, allocationSize = 1)
public class RankProps {

    @Id
    @GeneratedValue(generator = RANK_PROPS_GEN, strategy = GenerationType.SEQUENCE)
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
