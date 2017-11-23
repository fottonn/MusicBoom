package ru.bugmakers.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by Ivan
 */
@Entity
@Table(name = "user_authority")
public class UserAuthority implements GrantedAuthority{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "authority")
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
