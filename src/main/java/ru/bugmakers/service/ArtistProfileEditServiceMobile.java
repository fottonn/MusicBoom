package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.mobile.ArtistEditRequestMobile;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.enrichers.UserDTO2UserEnricher;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    @Autowired
    private SaveImagesService saveImagesService;

    public Boolean artistProfileEdit(ArtistEditRequestMobile artistEditingResponseMobile) throws MbException {
        UserDTO userDTO = artistEditingResponseMobile.getUserDTO();
        User user = userService.findUserByEmail(userDTO.getEmail());
        if (user != null) {
            if (user.getUserType().equals(UserType.LISTENER)) {
                userDTO2UserEnricher.enrich(userDTO, user);
                User resultUser = userService.updateUser(user);
                if (resultUser == null) {
                    throw MbException.create(MbError.APE03);
                }
            } else {
                throw MbException.create(MbError.APE02);
            }
        } else {
            throw MbException.create(MbError.APE01);
        }
        return Boolean.TRUE;
    }

    public Boolean artistAvatarChange(String id, MultipartFile file, HttpServletRequest req) throws MbException, IOException {
        String fileName;
        User user = isUserExist(id);
        fileName = saveImagesService.saveFile(file, req.getServletContext().getRealPath("/WEB-INF/static/img/"));
        if (fileName != null) {
            user.setAvatar(fileName);
            User savedUser = userService.updateUser(user);
            if (savedUser == null) {
                throw MbException.create(MbError.APE03);
            }
        } else {
            throw MbException.create(MbError.APE04);
        }
        return Boolean.TRUE;
    }

    public Boolean artistPhoneChange(String id, String phoneNumber) throws MbException {
        User user = isUserExist(id);
        user.setPhone(phoneNumber);
        User savedUser = userService.updateUser(user);
        if (savedUser == null) {
            throw MbException.create(MbError.APE03);
        }
        return Boolean.TRUE;
    }

    public User isUserExist(String id) throws MbException {
        Optional<User> user = userService.findUserById(id);
        if (!user.isPresent()) {
            throw MbException.create(MbError.APE01);
        } else {
            return user.get();
        }
    }
}
