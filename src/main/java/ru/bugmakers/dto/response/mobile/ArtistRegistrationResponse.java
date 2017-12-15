package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.Photos;
import ru.bugmakers.dto.StatOfPerformance;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class ArtistRegistrationResponse extends CommonResponseToMobile implements ResponseToMobile {
    public ArtistRegistrationResponse(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
    private String email;
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
    private String isOrdered;
    private String regDate;
    private String allEarnedMoney;
    private String allDerivedMoney;
    private String cityRainting;
    private String countryRating;
    private String currentBalance;
    private String allDonatedArtists;
    private Photos photos;
    private StatOfPerformance statOfPerformance;

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

    public String getIsOrdered() {
        return isOrdered;
    }

    public void setIsOrdered(String isOrdered) {
        this.isOrdered = isOrdered;
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

    public String getCityRainting() {
        return cityRainting;
    }

    public void setCityRainting(String cityRainting) {
        this.cityRainting = cityRainting;
    }

    public String getCountryRating() {
        return countryRating;
    }

    public void setCountryRating(String countryRating) {
        this.countryRating = countryRating;
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

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public StatOfPerformance getStatOfPerformance() {
        return statOfPerformance;
    }

    public void setStatOfPerformance(StatOfPerformance statOfPerformance) {
        this.statOfPerformance = statOfPerformance;
    }
}
