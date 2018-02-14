package ru.bugmakers.entity;

import ru.bugmakers.enums.FeedBackType;

import javax.persistence.*;

/**
 * Created by Ayrat on 16.11.2017.
 */
@Entity
@Table(name = "feedback")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "feed_back_type")
    @Enumerated(EnumType.STRING)
    private FeedBackType feedBackType;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
