package ru.bugmakers.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bugmakers.dto.common.UserDTO;

import java.util.List;

/**
 * Created by Ivan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse extends MbResponse {

    private UserDTO user;
    private List<UserDTO> artists;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<UserDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<UserDTO> artists) {
        this.artists = artists;
    }
}
