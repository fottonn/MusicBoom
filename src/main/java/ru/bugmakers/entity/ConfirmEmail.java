package ru.bugmakers.entity;

import javax.persistence.*;

/**
 * Created by Ayrat on 01.02.2018.
 */
@Entity
@Table(name = "confirm_email")
public class ConfirmEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "generated_value")
    private String generatedValue;

    public String getGeneratedValue() {
        return generatedValue;
    }

    public void setGeneratedValue(String generatedValue) {
        this.generatedValue = generatedValue;
    }
}
