package ru.bugmakers.entity.auth;

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

    public VkAuth(String socialId) {
        super(socialId);
    }

}
