package ru.bugmakers.dto.request.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.common.UserDTO;

import java.util.Set;

/**
 * Created by Ayrat on 11.12.2017.
 */
public class ArtistEditRqWeb {

    @JsonProperty("user")
    private UserDTO userDTO;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("old_password")
    private String oldPassword;

    @JsonProperty("new_password")
    private String newPassword;

    @JsonProperty("photos_id")
    private Set<String> photoIds;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Set<String> getPhotoIds() {
        return photoIds;
    }

    public void setPhotoIds(Set<String> photoIds) {
        this.photoIds = photoIds;
    }
}
