package ru.bugmakers.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.bugmakers.dto.Photos;
import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ayrat on 21.11.2017.
 */

public class RegistrationArtistRequestMobile {
    @JsonProperty("User")
    private UserDTO userDTO;
    private Photos photos;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}
