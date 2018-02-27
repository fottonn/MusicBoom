package ru.bugmakers.dto.request.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ivan
 */
public class ArtistPasswordChangeRqWeb extends SessionDataRequest{

    @JsonProperty("old_password")
    private String oldPassword;

    @JsonProperty("new_password")
    private String newPassword;

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

}
