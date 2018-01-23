package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.mobile.ArtistEditRequestMobile;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.converters.UserDto2UserConverter;
import ru.bugmakers.mappers.enrichers.UserDTO2UserEnricher;

import java.util.Optional;

/**
 * Created by Ayrat on 17.01.2018.
 */
@Service
public class ArtistProfileEditServiceMobile {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDTO2UserEnricher userDTO2UserEnricher;
    public Boolean artistProfileEdit(ArtistEditRequestMobile artistEditingResponseMobile) throws MbException {
        UserDTO userDTO = artistEditingResponseMobile.getUserDTO();
        User user = userService.findUserByEmail(userDTO.getEmail());
        if (user != null) {
            if (user.getUserType().equals(UserType.LISTENER)) {
                userDTO2UserEnricher.enrich(userDTO, user);
                User resultUser = userService.updateUser(user);
                return resultUser != null ? Boolean.TRUE : Boolean.FALSE;
            }else {
                throw MbException.create(MbError.APE02);
            }
        }else{
            throw MbException.create(MbError.APE01);
        }
    }
}
