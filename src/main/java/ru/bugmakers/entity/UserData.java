package ru.bugmakers.entity;

import ru.bugmakers.enums.UserType;

import javax.persistence.*;
import java.util.GregorianCalendar;

/**
 * Created by Ayrat on 16.11.2017.
 */
@javax.persistence.Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birthday")
    private GregorianCalendar birthDay;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "registartion_date")
    private GregorianCalendar registartionDate;

    @Column(name = "public_name")
    private String publicName;

    @Column(name = "email")
    private String email;

    @Column(name = "user_type")
    private UserType userType;

    @Column(name = "phone")
    private String phone;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public GregorianCalendar getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(GregorianCalendar birthDay) {
        this.birthDay = birthDay;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public GregorianCalendar getRegistartionDate() {
        return registartionDate;
    }

    public void setRegistartionDate(GregorianCalendar registartionDate) {
        this.registartionDate = registartionDate;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
