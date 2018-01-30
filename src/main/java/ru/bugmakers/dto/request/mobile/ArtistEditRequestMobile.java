package ru.bugmakers.dto.request.mobile;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.SessionDataRequest;

/**
 * Created by Ayrat on 21.11.2017.
 */
public class ArtistEditRequestMobile extends SessionDataRequest {
    private UserDTO userDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
