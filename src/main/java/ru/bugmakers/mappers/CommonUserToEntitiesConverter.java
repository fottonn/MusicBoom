package ru.bugmakers.mappers;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.mobile.RegistrationArtistRequestMobile;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ayrat on 27.12.2017.
 */
public class CommonUserToEntitiesConverter {

    public static final String D_M_YYYY = "d.M.yyyy";
    final DateTimeFormatter formatter  = DateTimeFormatter.ofPattern(D_M_YYYY);

    public UserDTO convert(RegistrationArtistRequestMobile userRequest) {
        User user = new User();
        UserDTO userDTO = userRequest.getUserDTO();
        user.setCity(userDTO.getCity());
        user.setName(userDTO.getName());
        user.setSurName(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPatronymic(userDTO.getPatronimyc());
        user.setNickname(userDTO.getNickname());
        user.setBirthDay(LocalDate.parse(userDTO.getBirthday(), formatter));
        user.setPhone(userDTO.getPhoneNumber());
        user.setCountry(userDTO.getCountry());
        user.setCity(userDTO.getCity());
        user.setRegistrationDate(LocalDateTime.now());


        return userDTO;
    }
}
