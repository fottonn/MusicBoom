package ru.bugmakers.entity.auth;

import ru.bugmakers.entity.User;

import javax.persistence.*;

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

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "oauth_token")
    private OauthToken oauthToken;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SocialAuth() {
    }

    public SocialAuth(String socialId, OauthToken oauthToken) {
        this.socialId = socialId;
        this.oauthToken = oauthToken;
    }

    public SocialAuth(String socialId, OauthToken oauthToken, User user) {
        this.socialId = socialId;
        this.oauthToken = oauthToken;
        this.user = user;
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

    public OauthToken getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(OauthToken oauthToken) {
        this.oauthToken = oauthToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
