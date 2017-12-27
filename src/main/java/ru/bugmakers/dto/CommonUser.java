package ru.bugmakers.dto;

import ru.bugmakers.enums.UserType;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class CommonUser {
    private String email;
    private UserType userType;
    private String password;
    private String confirmedPassword;
    private String name;
    private String surname;
    private String patronymic;
    private String nickname;
    private String birthday;
    private String isPhone;
    private String phoneNumber;
    private String creativity;
    private String instrument;
    private String genre;
    private String country;
    private String city;
    private String vk;
    private String tlg;
    private String wapp;
    private String isPersonalData;
    private String isContract;
    private String cityRaiting;
    private String countryRaiting;
    private Boolean isOrdered;
    private String regDate;
    private String allEarnedMoney;
    private String allDerivedMoney;
    private String currentBalance;
    private String allDonatedArtists;
    private String isLinkedCard;
    private String isAllowsOfPersonalData;
    private String isArtistContract;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Boolean getOrdered() {
        return isOrdered;
    }

    public void setOrdered(Boolean ordered) {
        isOrdered = ordered;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getAllEarnedMoney() {
        return allEarnedMoney;
    }

    public void setAllEarnedMoney(String allEarnedMoney) {
        this.allEarnedMoney = allEarnedMoney;
    }

    public String getAllDerivedMoney() {
        return allDerivedMoney;
    }

    public void setAllDerivedMoney(String allDerivedMoney) {
        this.allDerivedMoney = allDerivedMoney;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getAllDonatedArtists() {
        return allDonatedArtists;
    }

    public void setAllDonatedArtists(String allDonatedArtists) {
        this.allDonatedArtists = allDonatedArtists;
    }

    public String getIsLinkedCard() {
        return isLinkedCard;
    }

    public void setIsLinkedCard(String isLinkedCard) {
        this.isLinkedCard = isLinkedCard;
    }

    public String getIsAllowsOfPersonalData() {
        return isAllowsOfPersonalData;
    }

    public void setIsAllowsOfPersonalData(String isAllowsOfPersonalData) {
        this.isAllowsOfPersonalData = isAllowsOfPersonalData;
    }

    public String getIsArtistContract() {
        return isArtistContract;
    }

    public void setIsArtistContract(String isArtistContract) {
        this.isArtistContract = isArtistContract;
    }

    public String getCityRaiting() {
        return cityRaiting;
    }

    public void setCityRaiting(String cityRaiting) {
        this.cityRaiting = cityRaiting;
    }

    public String getCountryRaiting() {
        return countryRaiting;
    }

    public void setCountryRaiting(String countryRaiting) {
        this.countryRaiting = countryRaiting;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIsPhone() {
        return isPhone;
    }

    public void setIsPhone(String isPhone) {
        this.isPhone = isPhone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreativity() {
        return creativity;
    }

    public void setCreativity(String creativity) {
        this.creativity = creativity;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public String getWapp() {
        return wapp;
    }

    public void setWapp(String wapp) {
        this.wapp = wapp;
    }

    public String getIsPersonalData() {
        return isPersonalData;
    }

    public void setIsPersonalData(String isPersonalData) {
        this.isPersonalData = isPersonalData;
    }

    public String getIsContract() {
        return isContract;
    }

    public void setIsContract(String isContract) {
        this.isContract = isContract;
    }
}
