package ru.bugmakers.entity.auth;

import ru.bugmakers.entity.User;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ivan
 */
@Entity
@Table(name = "fb_auth")
public class FbAuth extends SocialAuth{

    public FbAuth() {
    }

    public FbAuth(String socialId, OauthToken oauthToken) {
        super(socialId, oauthToken);
    }

    public FbAuth(String socialId, OauthToken oauthToken, User user) {
        super(socialId, oauthToken, user);
    }
}
