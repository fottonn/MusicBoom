package ru.bugmakers.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.mobile.RegistrationArtistRequestMobile;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.Role;
import ru.bugmakers.enums.Sex;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayrat on 27.12.2017.
 */
@Component
public class UserDtoToEntitiesConverter {
    public PasswordEncoder passwordEncoder;

    public static final String D_M_YYYY = "d.M.yyyy";
    final DateTimeFormatter formatter  = DateTimeFormatter.ofPattern(D_M_YYYY);

     @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convert(RegistrationArtistRequestMobile userRequest) {
        User user = new User();
        UserDTO userDTO = userRequest.getUserDTO();
        user.setCity(userDTO.getCity());
        user.setName(userDTO.getName());
        user.setSurName(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPatronymic(userDTO.getPatronimyc());
        user.setNickname(userDTO.getNickname());
        user.setBirthDay(LocalDate.parse(userDTO.getBirthday(), formatter));
        user.setPhone(userDTO.getPhoneNumber());
        user.setCountry(userDTO.getCountry());
        user.setCity(userDTO.getCity());
        user.setRegistrationDate(LocalDateTime.now());
        user.setPublicName(userDTO.getNickname());
        user.setUserType(UserType.ARTIST);
        user.setSex(Sex.NONE);
        user.setRoles(new ArrayList<Role>() {{
            add(Role.ARTIST);
        }});
        user.setVkContact(userDTO.getVk());
        user.setTlgContact(userDTO.getTlg());
        user.setWhatsappContact(userDTO.getWapp());
        user.setIsAllowOfPersonalData(userDTO.getIsAllowOfPersonalData());
        user.setIsArtistContact(userDTO.getIsArtistContact());
        return user;
    }
}
