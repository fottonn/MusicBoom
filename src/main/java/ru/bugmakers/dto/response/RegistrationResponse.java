package ru.bugmakers.dto.response;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
public class RegistrationResponse extends MbResponse{

    private UserDTO user;

    public RegistrationResponse(MbException e, RsStatus status) {
        super(e, status);
    }

    public RegistrationResponse(RsStatus status) {
        super(status);
    }

    public RegistrationResponse(MbException e) {
        super(e);
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
