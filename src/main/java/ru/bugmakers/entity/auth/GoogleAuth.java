package ru.bugmakers.entity.auth;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ivan
 */
@Entity
@Table(name = "google_auth")
public class GoogleAuth extends SocialAuth{
    public GoogleAuth() {
    }

    public GoogleAuth(String socialId) {
        super(socialId);
    }
}
