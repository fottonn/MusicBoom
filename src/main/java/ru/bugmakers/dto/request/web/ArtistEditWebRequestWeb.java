package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ayrat on 11.12.2017.
 */
public class ArtistEditWebRequestWeb {
    private UserDTO userDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
