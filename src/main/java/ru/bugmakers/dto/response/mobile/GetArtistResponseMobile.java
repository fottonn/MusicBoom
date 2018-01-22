package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class GetArtistResponseMobile extends MbResponseToMobile {

    private UserDTO userDTO;

    public GetArtistResponseMobile(MbException e, RsStatus status) {
        super(e, status);
    }

    public GetArtistResponseMobile(RsStatus status) {
        super(status);
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
