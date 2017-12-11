package ru.bugmakers.dto.request.web;

/**
 * Created by Ayrat on 11.12.2017.
 */
public class ArtistEditWebRequest {
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

    private String creativity;
    private String instrument;
    private String genre;
    private String aboutMe;
    private String setInvitable;

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

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getPublic_name() {
        return public_name;
    }

    public void setPublic_name(String public_name) {
        this.public_name = public_name;
    }

    public String getCreativity() {
        return creativity;
    }

    public void setCreativity(String creativity) {
        this.creativity = creativity;
    }

    public String getUnstrument() {
        return instrument;
    }

    public void setUnstrument(String unstrument) {
        this.instrument = unstrument;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getSetInvitable() {
        return setInvitable;
    }

    public void setSetInvitable(String setInvitable) {
        this.setInvitable = setInvitable;
    }
}
