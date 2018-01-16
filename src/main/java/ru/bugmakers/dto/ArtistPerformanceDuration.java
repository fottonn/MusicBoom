package ru.bugmakers.dto;


import ru.bugmakers.dto.common.UserDTO;

/**
 * Created by Ayrat on 11.12.2017.
 */
public class ArtistPerformanceDuration {
    private UserDTO userDTO;
    private Period period;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
