package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.dto.request.mobile.ArtistEditRequestMobile;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

import java.time.LocalDate;

/**
 * Created by Ayrat on 17.01.2018.
 */
@Service
public class ArtistProfileEditServiceMobile {
    @Autowired
    private UserService userService;

    public Boolean artistProfileEdit(ArtistEditRequestMobile artistEditingResponseMobile) throws MbException {
        User user = userService.findUserByEmail(artistEditingResponseMobile.getEmail());
        if (user != null) {
            if (user.getUserType().equals(UserType.LISTENER)) {
                user.setEmail(artistEditingResponseMobile.getEmail());
                user.setName(artistEditingResponseMobile.getName());
                user.setSurName(artistEditingResponseMobile.getSurname());
                user.setPatronymic(artistEditingResponseMobile.getPatronymic());
                user.setBirthDay(LocalDate.parse(artistEditingResponseMobile.getBirthday()));
                user.setNickname(artistEditingResponseMobile.getNickname());
                user.setCountry(artistEditingResponseMobile.getCountry());
                user.setCity(artistEditingResponseMobile.getCity());
                user.setVkContact(artistEditingResponseMobile.getVk());
                user.setTlgContact(artistEditingResponseMobile.getTlg());
                user.setWhatsappContact(artistEditingResponseMobile.getWhatsapp());
                userService.updateUser(user);
            }else {
                throw new MbException(MbError.APE02);
            }
        }else{
            throw new MbException(MbError.APE01);
        }

        return null;
    }
}
