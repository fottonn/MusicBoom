package ru.bugmakers.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.SessionDataRequest;

import java.util.Set;

/**
 * Created by Ayrat on 21.11.2017.
 */
public class ArtistEditRqMobile extends SessionDataRequest {

    @JsonProperty("user")
    private UserDTO userDTO;

    @JsonProperty("old_password")
    private String oldPassword;

    @JsonProperty("new_password")
    private String newPassword;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("creativity")
    private String creativity;

    @JsonProperty("instrument")
    private String instrument;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("orderable")
    private Boolean orderable;

    @JsonProperty("photos_id")
    private Set<String> photoIds;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public Boolean isOrderable() {
        return orderable;
    }

    public Boolean getOrderable() {
        return orderable;
    }

    public Set<String> getPhotoIds() {
        return photoIds;
    }

    public void setPhotoIds(Set<String> photoIds) {
        this.photoIds = photoIds;
    }
}
