package ru.bugmakers.dto.request.mobile;

import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 21.11.2017.
 */
public class ArtistEditRequestMobile extends SessionDataRequest {
    /**
     * email
     */
    private String email;
    /**
     * Имя
     */
    private String name;
    /**
     * Фамилия
     */
    private String surname;
    /**
     * Отчество
     */
    private String patronymic;
    /**
     * Дата рождения
     */
    private String birthday;
    /**
     * Никнейм
     */
    private String nickname;
    /**
     * Страна
     */
    private String country;
    /**
     * Город
     */
    private String city;
    private String vk;
    private String tlg;
    private String whatsapp;
    /**
     * Публичное имя
     */
    private String public_name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public String getTlg() {
        return tlg;
    }

    public void setTlg(String tlg) {
        this.tlg = tlg;
    }

    public String getPublic_name() {
        return public_name;
    }

    public void setPublic_name(String public_name) {
        this.public_name = public_name;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
