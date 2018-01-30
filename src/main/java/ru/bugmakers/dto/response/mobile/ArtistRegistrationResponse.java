package ru.bugmakers.dto.response.mobile;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 15.12.2017.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ArtistRegistrationResponse extends MbResponseToMobile {

    private UserDTO user;

    public ArtistRegistrationResponse(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistRegistrationResponse(RsStatus status) {
        super(status);
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
