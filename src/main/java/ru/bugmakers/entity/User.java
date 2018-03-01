package ru.bugmakers.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import ru.bugmakers.entity.auth.FbAuth;
import ru.bugmakers.entity.auth.GoogleAuth;
import ru.bugmakers.entity.auth.VkAuth;
import ru.bugmakers.enums.Role;
import ru.bugmakers.enums.Sex;
import ru.bugmakers.enums.UserType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static ru.bugmakers.entity.EntityConstants.*;

/**
 * Created by Ayrat on 16.11.2017.
 */
@Entity
@Table(name = "user_t")
@SecondaryTables({
        @SecondaryTable(name = USER_LOGIN, pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id")),
        @SecondaryTable(name = VK_CONTACT, pkJoinColumns = @PrimaryKeyJoinColumn(name = "vk_contact_id")),
        @SecondaryTable(name = WHATSAPP_CONTACT, pkJoinColumns = @PrimaryKeyJoinColumn(name = "whatsapp_contact_id")),
        @SecondaryTable(name = TLG_CONTACT, pkJoinColumns = @PrimaryKeyJoinColumn(name = "tlg_contact_id"))})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "registered")
    private boolean registered = false;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "birthday")
    @Type(type = LOCAL_DATE_TYPE)
    private LocalDate birthDay;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "registration_date")
    @Type(type = LOCAL_DATE_TIME_TYPE)
    private LocalDateTime registrationDate;

    @Column(name = "public_name")
    private String publicName;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private Email email;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex = Sex.NONE;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    @Column(name = "login", table = USER_LOGIN)
    private String login;

    @Column(name = "password", table = USER_LOGIN)
    private String password;

    @Column(name = "enabled", table = USER_LOGIN)
    private boolean enabled = true;

    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private VkAuth vkAuth;

    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private FbAuth fbAuth;

    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private GoogleAuth googleAuth;

    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private ArtistInfo artistInfo;

    @Column(name = "vk_link", table = VK_CONTACT)
    private String vkContact;

    @Column(name = "tlg_link", table = TLG_CONTACT)
    private String tlgContact;

    @Column(name = "whatsapp_link", table = WHATSAPP_CONTACT)
    private String whatsappContact;

    @Column(name = "isAllowOfPersonalData")
    private String isAllowOfPersonalData;

    @Column(name = "isArtistContact")
    private String isArtistContact;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> senderTransactions;

    @OneToMany(mappedBy = "recipient")
    private List<Transaction> recipientTransactions;

    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private ArtistRating artistRating;

    @ElementCollection
    @CollectionTable(name = "photos", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "photo")
    private List<String> photos;

    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "user")
    private List<FeedBack> feedBacks;

    @Column(name = "card_number")
    private String cardNumber;

    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
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

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRoles(Role... roles) {
        this.roles = roles != null ? new HashSet<>(Arrays.asList(roles)) : null;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public VkAuth getVkAuth() {
        return vkAuth;
    }

    public void setVkAuth(VkAuth vkAuth) {
        this.vkAuth = vkAuth;
    }

    public FbAuth getFbAuth() {
        return fbAuth;
    }

    public void setFbAuth(FbAuth fbAuth) {
        this.fbAuth = fbAuth;
    }

    public GoogleAuth getGoogleAuth() {
        return googleAuth;
    }

    public void setGoogleAuth(GoogleAuth googleAuth) {
        this.googleAuth = googleAuth;
    }

    public ArtistInfo getArtistInfo() {
        return artistInfo;
    }

    public void setArtistInfo(ArtistInfo artistInfo) {
        this.artistInfo = artistInfo;
    }

    public String getVkContact() {
        return vkContact;
    }

    public void setVkContact(String vkContact) {
        this.vkContact = vkContact;
    }

    public String getTlgContact() {
        return tlgContact;
    }

    public void setTlgContact(String tlgContact) {
        this.tlgContact = tlgContact;
    }

    public String getWhatsappContact() {
        return whatsappContact;
    }

    public void setWhatsappContact(String whatsappContact) {
        this.whatsappContact = whatsappContact;
    }

    public List<Event> getEvents() {
        if (events == null) events = new ArrayList<>();
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public ArtistRating getArtistRating() {
        return artistRating;
    }

    public void setArtistRating(ArtistRating artistRating) {
        this.artistRating = artistRating;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIsAllowOfPersonalData() {
        return isAllowOfPersonalData;
    }

    public void setIsAllowOfPersonalData(String isAllowOfPersonalData) {
        this.isAllowOfPersonalData = isAllowOfPersonalData;
    }

    public String getIsArtistContact() {
        return isArtistContact;
    }

    public void setIsArtistContact(String isArtistContact) {
        this.isArtistContact = isArtistContact;
    }

    public List<FeedBack> getFeedBacks() {
        return feedBacks;
    }

    public void setFeedBacks(List<FeedBack> feedBacks) {
        this.feedBacks = feedBacks;
    }

    public User withRegistered(boolean registered) {
        this.registered = registered;
        return this;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }

    public User withSurName(String surName) {
        this.surName = surName;
        return this;
    }

    public User withPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public User withAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
        return this;
    }

    public User withBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public User withCountry(String country) {
        this.country = country;
        return this;
    }

    public User withCity(String city) {
        this.city = city;
        return this;
    }

    public User withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public User withRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public User withPublicName(String publicName) {
        this.publicName = publicName;
        return this;
    }

    public User withEmail(Email email) {
        this.email = email;
        return this;
    }

    public User withUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public User withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public User withSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public User withRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User withLogin(String login) {
        this.login = login;
        return this;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public User withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public User withVkAuth(VkAuth vkAuth) {
        this.vkAuth = vkAuth;
        return this;
    }

    public User withFbAuth(FbAuth fbAuth) {
        this.fbAuth = fbAuth;
        return this;
    }

    public User withGoogleAuth(GoogleAuth googleAuth) {
        this.googleAuth = googleAuth;
        return this;
    }

    public User withArtistInfo(ArtistInfo artistInfo) {
        this.artistInfo = artistInfo;
        return this;
    }

    public User withVkContact(String vkContact) {
        this.vkContact = vkContact;
        return this;
    }

    public User withTlgContact(String tlgContact) {
        this.tlgContact = tlgContact;
        return this;
    }

    public User withWhatsappContact(String whatsappContact) {
        this.whatsappContact = whatsappContact;
        return this;
    }

    public User withIsAllowOfPersonalData(String isAllowOfPersonalData) {
        this.isAllowOfPersonalData = isAllowOfPersonalData;
        return this;
    }

    public User withIsArtistContact(String isArtistContact) {
        this.isArtistContact = isArtistContact;
        return this;
    }

    public User withEvents(List<Event> events) {
        this.events = events;
        return this;
    }

    public User withSenderTransactions(List<Transaction> senderTransactions) {
        this.senderTransactions = senderTransactions;
        return this;
    }

    public User withRecipientTransactions(List<Transaction> recipientTransactions) {
        this.recipientTransactions = recipientTransactions;
        return this;
    }

    public User withArtistRating(ArtistRating artistRating) {
        this.artistRating = artistRating;
        return this;
    }

    public User withPhotos(List<String> photos) {
        this.photos = photos;
        return this;
    }

    public User withAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
}
