package ru.bugmakers.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Ivan
 */
@JsonRootName("user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {

    private String sessionId;
    private String userType;
    private String password;
    private String id;
    private String name;
    private String surname;
    private String patronimyc;
    private String birthday;
    private String sex;
    private String nickname;
    private String country;
    private String city;
    private String phoneNumber;
    private String email;
    private String creativity;
    private String instrument;
    private String genre;
    private String vk;
    private String tlg;
    private String wapp;
    private Boolean isOrdered;
    private LocalDate regDate;
    private String allEarnedMoney;
    private String allDerivedMoney;
    private Double cityRating;
    private Double countryRating;
    private String currentBalance;
    private Integer allDonatedArtists;
    private Boolean isLinkedCard;
    private StatOfPerfomanceDTO statOfPerfomance;

    public UserDTO() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private UserDTO(String sessionId, String userType, String id, String name, String surname, String patronimyc, String birthday, String sex, String nickname, String country, String city, String phoneNumber, String email, String creativity, String instrument, String genre, String vk, String tlg, String wapp, Boolean isOrdered, LocalDate regDate, String allEarnedMoney, String allDerivedMoney, Double cityRating, Double countryRating, String currentBalance, Integer allDonatedArtists, Boolean isLinkedCard, StatOfPerfomanceDTO statOfPerfomance) {
        this.sessionId = sessionId;
        this.userType = userType;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronimyc = patronimyc;
        this.birthday = birthday;
        this.sex = sex;
        this.nickname = nickname;
        this.country = country;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.creativity = creativity;
        this.instrument = instrument;
        this.genre = genre;
        this.vk = vk;
        this.tlg = tlg;
        this.wapp = wapp;
        this.isOrdered = isOrdered;
        this.regDate = regDate;
        this.allEarnedMoney = allEarnedMoney;
        this.allDerivedMoney = allDerivedMoney;
        this.cityRating = cityRating;
        this.countryRating = countryRating;
        this.currentBalance = currentBalance;
        this.allDonatedArtists = allDonatedArtists;
        this.isLinkedCard = isLinkedCard;
        this.statOfPerfomance = statOfPerfomance;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPatronimyc() {
        return patronimyc;
    }

    public void setPatronimyc(String patronimyc) {
        this.patronimyc = patronimyc;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getOrdered() {
        return isOrdered;
    }

    public void setOrdered(Boolean ordered) {
        isOrdered = ordered;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
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

    public Double getCityRating() {
        return cityRating;
    }

    public void setCityRating(Double cityRating) {
        this.cityRating = cityRating;
    }

    public Double getCountryRating() {
        return countryRating;
    }

    public void setCountryRating(Double countryRating) {
        this.countryRating = countryRating;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Integer getAllDonatedArtists() {
        return allDonatedArtists;
    }

    public void setAllDonatedArtists(Integer allDonatedArtists) {
        this.allDonatedArtists = allDonatedArtists;
    }

    public Boolean getLinkedCard() {
        return isLinkedCard;
    }

    public void setLinkedCard(Boolean linkedCard) {
        isLinkedCard = linkedCard;
    }

    public StatOfPerfomanceDTO getStatOfPerfomance() {
        return statOfPerfomance;
    }

    public void setStatOfPerfomance(StatOfPerfomanceDTO statOfPerfomance) {
        this.statOfPerfomance = statOfPerfomance;
    }

    public UserDTO withSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public UserDTO withUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public UserDTO withId(String id) {
        this.id = id;
        return this;
    }

    public UserDTO withName(String name) {
        this.name = name;
        return this;
    }

    public UserDTO withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserDTO withPatronimyc(String patronimyc) {
        this.patronimyc = patronimyc;
        return this;
    }

    public UserDTO withBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public UserDTO withSex(String sex) {
        this.sex = sex;
        return this;
    }

    public UserDTO withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public UserDTO withCountry(String country) {
        this.country = country;
        return this;
    }

    public UserDTO withCity(String city) {
        this.city = city;
        return this;
    }

    public UserDTO withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDTO withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTO withCreativity(String creativity) {
        this.creativity = creativity;
        return this;
    }

    public UserDTO withInstrument(String instrument) {
        this.instrument = instrument;
        return this;
    }

    public UserDTO withGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public UserDTO withVk(String vk) {
        this.vk = vk;
        return this;
    }

    public UserDTO withTlg(String tlg) {
        this.tlg = tlg;
        return this;
    }

    public UserDTO withWapp(String wapp) {
        this.wapp = wapp;
        return this;
    }

    public UserDTO withIsOrdered(Boolean isOrdered) {
        this.isOrdered = isOrdered;
        return this;
    }

    public UserDTO withRegDate(LocalDate regDate) {
        this.regDate = regDate;
        return this;
    }

    public UserDTO withAllEarnedMoney(String allEarnedMoney) {
        this.allEarnedMoney = allEarnedMoney;
        return this;
    }

    public UserDTO withAllDerivedMoney(String allDerivedMoney) {
        this.allDerivedMoney = allDerivedMoney;
        return this;
    }

    public UserDTO withCityRating(Double cityRating) {
        this.cityRating = cityRating;
        return this;
    }

    public UserDTO withCountryRating(Double countryRating) {
        this.countryRating = countryRating;
        return this;
    }

    public UserDTO withCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
        return this;
    }

    public UserDTO withAllDonatedArtists(Integer allDonatedArtists) {
        this.allDonatedArtists = allDonatedArtists;
        return this;
    }

    public UserDTO withIsLinkedCard(Boolean isLinkedCard) {
        this.isLinkedCard = isLinkedCard;
        return this;
    }

    public UserDTO withStatOfPerfomance(StatOfPerfomanceDTO statOfPerfomance) {
        this.statOfPerfomance = statOfPerfomance;
        return this;
    }

}
