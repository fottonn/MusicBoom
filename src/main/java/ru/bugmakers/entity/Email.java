package ru.bugmakers.entity;


import javax.persistence.*;

/**
 * Created by Ayrat on 05.02.2018.
 */
@Entity
@Table(name = "value")
public class Email {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "enabled")
    private boolean enabled = false;

    @Column(name = "generated_value")
    private String confirmationCode;

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
}
