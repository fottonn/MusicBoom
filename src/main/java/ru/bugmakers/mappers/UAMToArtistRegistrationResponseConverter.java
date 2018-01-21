package ru.bugmakers.mappers;

import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.enums.RsStatus;

/**
 * Created by Ayrat on 15.01.2018.
 */
@Component
public class UAMToArtistRegistrationResponseConverter {
    public ArtistRegistrationResponse convert(UserDTO userDTO){
        ArtistRegistrationResponse artistRegistrationResponse = new ArtistRegistrationResponse(RsStatus.SUCCESS);
        artistRegistrationResponse.setUser(userDTO);
        return artistRegistrationResponse;
    }
}
