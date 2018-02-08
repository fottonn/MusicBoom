package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.MultipartFileDto;
import ru.bugmakers.dto.request.mobile.ArtistEditRequestMobile;
import ru.bugmakers.entity.ArtistInfo;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.Genre;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.enrichers.UserDTO2UserEnricher;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ayrat on 17.01.2018.
 */
@Service
public class ArtistProfileEditServiceMobile {
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private UserDTO2UserEnricher userDTO2UserEnricher;
    private SaveImagesService saveImagesService;
    private EmailConfirmationService emailConfirmationService;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setUserDTO2UserEnricher(UserDTO2UserEnricher userDTO2UserEnricher) {
        this.userDTO2UserEnricher = userDTO2UserEnricher;
    }
    @Autowired
    public void setSaveImagesService(SaveImagesService saveImagesService) {
        this.saveImagesService = saveImagesService;
    }
    @Autowired
    public void setEmailConfirmationService(EmailConfirmationService emailConfirmationService) {
        this.emailConfirmationService = emailConfirmationService;
    }

    public Boolean artistProfileEdit(UserDTO userDTO) throws MbException {
        User user = userService.findUserByEmail(userDTO.getEmail());
        if (user != null) {
            if (user.getUserType().equals(UserType.LISTENER)) {
                userDTO2UserEnricher.enrich(userDTO, user);
                if (user.getEmail() != null && !user.getEmail().isEnabled()) {
                      emailConfirmationService.sendConfirmationEmail(user);
                }
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

    public Boolean artistAvatarChange(String id, MultipartFile file) throws MbException, IOException {
        String fileName;
        User user = isUserExist(id);
        //TODO переделать путь сохранения файлов
        fileName = saveImagesService.saveFile(file, "/WEB-INF/static/img/");
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

    public Boolean artistPasswordChange(String id, String oldPassword, String newPassword) throws MbException {
        User user = isUserExist(id);
        if (user.getPassword().equals(passwordEncoder.encode(oldPassword))) {
            user.setPassword(passwordEncoder.encode(newPassword));
            User savedUser = userService.updateUser(user);
            if (savedUser == null) {
                throw MbException.create(MbError.APE03);
            }
        }else {
            throw MbException.create(MbError.APE05);
        }
        return Boolean.TRUE;
    }

    public Boolean artistCreativityChange(String id, String creativity) throws MbException {
        User user = isUserExist(id);
        ArtistInfo artistInfo = user.getArtistInfo();
        artistInfo.setCreativity(creativity);
        user.setArtistInfo(artistInfo);
        User savedUser = userService.updateUser(user);
        if (savedUser == null) {
            throw MbException.create(MbError.APE03);
        }
        return Boolean.TRUE;
    }

    public Boolean artistInstrumentChange(String id, String instrument) throws MbException {
        User user = isUserExist(id);
        ArtistInfo artistInfo = user.getArtistInfo();
        artistInfo.setInstrument(instrument);
        user.setArtistInfo(artistInfo);
        User savedUser = userService.updateUser(user);
        if (savedUser == null) {
            throw MbException.create(MbError.APE03);
        }
        return Boolean.TRUE;
    }

    public Boolean artistGenreChange(String id, String genre) throws MbException {
        User user = isUserExist(id);
        ArtistInfo artistInfo = user.getArtistInfo();
        artistInfo.setGenre(Genre.valueOf(genre));
        user.setArtistInfo(artistInfo);
        User savedUser = userService.updateUser(user);
        if (savedUser == null) {
            throw MbException.create(MbError.APE03);
        }
        return Boolean.TRUE;
    }

    public Boolean artistSetOrderableChange(String id, Boolean setOrderable) throws MbException {
        User user = isUserExist(id);
        ArtistInfo artistInfo = user.getArtistInfo();
        artistInfo.setOrdered(setOrderable);
        user.setArtistInfo(artistInfo);
        User savedUser = userService.updateUser(user);
        if (savedUser == null) {
            throw MbException.create(MbError.APE03);
        }
        return Boolean.TRUE;
    }

    public Boolean artistUploadPhotos(String id, MultipartFileDto uploadFiles) throws IOException, MbException {
        String savedFile;
        User user = isUserExist(id);
        for (MultipartFile multipartFile : uploadFiles.getMultipartFiles()) {
            savedFile = saveImagesService.saveFile(multipartFile, "/WEB-INF/static/img/");
            user.getPhotos().add(savedFile);
        }
        User savedUser = userService.updateUser(user);
        if (savedUser == null) {
            throw MbException.create(MbError.APE03);
        }
        return Boolean.TRUE;
    }

    public Boolean artistDeletePhotos(String id, List<String> photosId) throws MbException {
        User user = isUserExist(id);
        List<String> photos = user.getPhotos();
        for (String s : photosId) {
            if (photos.contains(s)) {
                photos.remove(s);
            }
        }
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
