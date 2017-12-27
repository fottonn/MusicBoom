package ru.bugmakers.entity;

import org.hibernate.annotations.Type;
import ru.bugmakers.enums.Role;
import ru.bugmakers.enums.UserType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.List;

import static ru.bugmakers.entity.EntityConstants.LOCAL_DATE_TIME_TYPE;

/**
 * Created by Ayrat on 16.11.2017.
 */
@Entity
@Table(name = "user_t")
@SecondaryTable(name = "user_login", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birthday")
    @Type(type = LOCAL_DATE_TIME_TYPE)
    private LocalDateTime birthDay;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "registration_date")
    private GregorianCalendar registrationDate;

    @Column(name = "public_name")
    private String publicName;

    @Column(name = "email")
    private String email;

    @Column(name = "user_type")
    private UserType userType;

    @Column(name = "phone")
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "roles_id"))
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @Column(name = "login", table = "user_login")
    private String login;

    @Column(name = "password", table = "user_login")
    private String password;

    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private ArtistInfo artistInfo;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "vk_id")
    private Vk vk;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "telegram_id")
    private TelegramContact telegramContact;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "whatsapp_id")
    private WhatsApp whatsApp;

    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private ActiveEvent activeEvent;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> senderTransactions;

    @OneToMany(mappedBy = "recipient")
    private List<Transaction> recipientTransactions;

    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private ArtistRating artistRating;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<UserPhoto> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDateTime birthDay) {
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

    public GregorianCalendar getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(GregorianCalendar registrationDate) {
        this.registrationDate = registrationDate;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArtistInfo getArtistInfo() {
        return artistInfo;
    }

    public void setArtistInfo(ArtistInfo artistInfo) {
        this.artistInfo = artistInfo;
    }

    public Vk getVk() {
        return vk;
    }

    public void setVk(Vk vk) {
        this.vk = vk;
    }

    public TelegramContact getTelegramContact() {
        return telegramContact;
    }

    public void setTelegramContact(TelegramContact telegramContact) {
        this.telegramContact = telegramContact;
    }

    public WhatsApp getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(WhatsApp whatsApp) {
        this.whatsApp = whatsApp;
    }

    public ActiveEvent getActiveEvent() {
        return activeEvent;
    }

    public void setActiveEvent(ActiveEvent activeEvent) {
        this.activeEvent = activeEvent;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Transaction> getSenderTransactions() {
        return senderTransactions;
    }

    public void setSenderTransactions(List<Transaction> senderTransactions) {
        this.senderTransactions = senderTransactions;
    }

    public List<Transaction> getRecipientTransactions() {
        return recipientTransactions;
    }

    public void setRecipientTransactions(List<Transaction> recipientTransactions) {
        this.recipientTransactions = recipientTransactions;
    }

    public ArtistRating getArtistRating() {
        return artistRating;
    }

    public void setArtistRating(ArtistRating artistRating) {
        this.artistRating = artistRating;
    }

    public List<UserPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<UserPhoto> photos) {
        this.photos = photos;
    }
}
