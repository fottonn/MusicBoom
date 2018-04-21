package ru.bugmakers.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.NameRepresentation;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Service
public class ArtistInfoPageService {

    private UserService userService;
    private PhotoService photoService;
    private ImagesService imagesService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Autowired
    public void setImagesService(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @Transactional
    public UserDTO getArtistForOtherUsers(Long id) throws MbException {
        User user = userService.findUserById(id);
        if (user == null) throw MbException.create(MbError.APE01);
        UserDTO userDTO = new UserDTO();
        userDTO
                .withPublicName(getPublicName(user))
                .withCreativity(user.getArtistInfo() != null ? user.getArtistInfo().getCreativity() : null)
                .withInstrument(user.getArtistInfo() != null ? user.getArtistInfo().getInstrument() : null)
                .withGenre(user.getArtistInfo() != null ? user.getArtistInfo().getGenre() : null)
                .withOrderable(user.getArtistInfo() != null && user.getArtistInfo().isOrderable())
                .withPhoneNumber(user.getPhone())
                .withVk(user.getVkContact())
                .withTlg(user.getTlgContact())
                .withWapp(user.getWhatsappContact())
                .withCityRating(user.getArtistRating() != null ? user.getArtistRating().getCityRatng() : null)
                .withCountryRating(user.getArtistRating() != null ? user.getArtistRating().getCountryRating() : null)
                .withAvatar(imagesService.fullImagePath(user.getAvatar()))
                .withPhotos(photoService.getPhotosByUserId(id));
        return userDTO;
    }

    private String getPublicName(User user) {
        if (user == null || user.getNameRepresentation() == null) return null;
        String publicName = null;
        switch (user.getNameRepresentation()) {
            case FULLNAME:
                String name = user.getName();
                String surName = user.getSurName();
                String patronimyc = user.getPatronymic();
                StringBuilder sb = new StringBuilder();
                if (StringUtils.isNotBlank(name)) sb.append(name);
                if (StringUtils.isNotBlank(patronimyc)) sb.append(" ").append(patronimyc);
                if (StringUtils.isNotBlank(surName)) sb.append(" ").append(surName);
                publicName = sb.toString();
                break;
            case NICKNAME:
                publicName = user.getNickname();
                break;
        }
        return publicName;
    }


}
