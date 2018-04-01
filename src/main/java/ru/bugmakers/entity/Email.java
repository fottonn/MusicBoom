package ru.bugmakers.entity;


import javax.persistence.*;

import static ru.bugmakers.entity.EntityConstants.EMAIL_GEN;
import static ru.bugmakers.entity.EntityConstants.EMAIL_SEQ;

/**
 * Created by Ayrat on 05.02.2018.
 */
@Entity
@Table(name = "email")
@SequenceGenerator(name = EMAIL_GEN, sequenceName = EMAIL_SEQ, allocationSize = 1)
public class Email {

    @Id
    @GeneratedValue(generator = EMAIL_GEN, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "enabled")
    private boolean enabled = false;

    @Column(name = "generated_value")
    private String confirmationCode;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Email() {
    }

    public Email(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
