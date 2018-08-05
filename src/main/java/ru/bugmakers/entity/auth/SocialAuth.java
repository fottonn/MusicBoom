package ru.bugmakers.entity.auth;

import ru.bugmakers.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by Ivan
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class SocialAuth {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "social_id")
    private String socialId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public SocialAuth() {
    }

    public SocialAuth(String socialId) {
        this.socialId = socialId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
