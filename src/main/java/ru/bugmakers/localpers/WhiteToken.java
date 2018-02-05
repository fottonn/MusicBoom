package ru.bugmakers.localpers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Ivan
 */
@Entity
@Table(name = "white_token")
public class WhiteToken {

    @Id
    private Long id;

    @Column(name = "token")
    private String token;

    public WhiteToken() {
    }

    public WhiteToken(Long id, String token) {
        this.id = id;
        this.token = token;
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
}
