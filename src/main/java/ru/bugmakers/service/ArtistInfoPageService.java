package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

import java.util.ArrayList;

/**
 * Created by Ivan
 */
@Service
public class ArtistInfoPageService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public UserDTO getArtistForOtherUsers(Long id) throws MbException {
        User user = userService.findUserById(id);
        if (user == null) throw MbException.create(MbError.APE01);
        UserDTO userDTO = new UserDTO();
        userDTO
                .withName(user.getName())
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
                .withAvatar(user.getAvatar()) //TODO имя или путь?
                .withPhotos(new ArrayList<>(user.getPhotos())); //TODO имя или путь
        return userDTO;
    }


}
