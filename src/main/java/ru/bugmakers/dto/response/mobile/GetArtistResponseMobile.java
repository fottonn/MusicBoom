package ru.bugmakers.dto.response.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.MbResponse;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class GetArtistResponseMobile extends MbResponse {

    @JsonProperty("user")
    private UserDTO userDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
