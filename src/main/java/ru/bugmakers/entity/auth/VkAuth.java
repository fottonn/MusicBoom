package ru.bugmakers.entity.auth;

import ru.bugmakers.entity.User;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ivan
 */
@Entity
@Table(name = "vk_auth")
public class VkAuth extends SocialAuth {

    public VkAuth() {
    }

    public VkAuth(String socialId, OauthToken oauthToken) {
        super(socialId, oauthToken);
    }

    public VkAuth(String socialId, OauthToken oauthToken, User user) {
        super(socialId, oauthToken, user);
    }
}
