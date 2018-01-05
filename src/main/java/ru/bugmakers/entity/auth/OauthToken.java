package ru.bugmakers.entity.auth;

import javax.persistence.*;

/**
 * Created by Ivan
 */
@Entity
@Table(name = "oauth_token")
public class OauthToken {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "expire_time")
    private String expireTime;

    public OauthToken() {
    }

    public OauthToken(String token) {
        this.token = token;
    }

    public OauthToken(String token, String expireTime) {
        this.token = token;
        this.expireTime = expireTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
