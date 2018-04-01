package ru.bugmakers.entity;

import ru.bugmakers.enums.FeedBackType;

import javax.persistence.*;

import static ru.bugmakers.entity.EntityConstants.FEED_BACK_GEN;
import static ru.bugmakers.entity.EntityConstants.FEED_BACK_SEQ;

/**
 * Created by Ayrat on 16.11.2017.
 */
@Entity
@Table(name = "feedback")
@SequenceGenerator(name = FEED_BACK_GEN, sequenceName = FEED_BACK_SEQ, allocationSize = 1)
public class FeedBack {

    @Id
    @GeneratedValue(generator = FEED_BACK_GEN, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "feed_back_type")
    @Enumerated(EnumType.STRING)
    private FeedBackType feedBackType;

    @Column(name = "text")
    private String text;

    public FeedBack() {
    }

    public FeedBack(Long userId, FeedBackType feedBackType, String text) {
        this.userId = userId;
        this.feedBackType = feedBackType;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FeedBackType getFeedBackType() {
        return feedBackType;
    }

    public void setFeedBackType(FeedBackType feedBackType) {
        this.feedBackType = feedBackType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
