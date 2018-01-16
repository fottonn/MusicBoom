package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.Photos;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class GetArtistResponseMobile extends MbResponseToMobile {

    private Photos photos;
    private UserDTO userDTO;

    public GetArtistResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public GetArtistResponseMobile(RsStatus status) {
        super(status);
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
