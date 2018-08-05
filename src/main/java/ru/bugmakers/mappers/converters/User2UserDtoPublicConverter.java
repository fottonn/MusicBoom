package ru.bugmakers.mappers.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.service.ImagesService;

/**
 * Created by Ivan
 */
@Component
public class User2UserDtoPublicConverter implements MbConverter<User, UserDTO> {

    private ImagesService imagesService;

    @Autowired
    public void setImagesService(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @Override
    public UserDTO convert(User source) {
        if (source == null) return null;
        return new UserDTO()
                .withId(source.getId().toString())
                .withName(source.getName())
                .withSurname(source.getSurName())
                .withPatronimyc(source.getPatronymic())
                .withAboutMe(source.getAboutMe())
                .withNickname(source.getNickname())
                .withCountry(source.getCountry())
                .withCity(source.getCity())
                .withCreativity(source.getArtistInfo() != null ? source.getArtistInfo().getCreativity() : null)
                .withInstrument(source.getArtistInfo() != null ? source.getArtistInfo().getInstrument() : null)
                .withGenre(source.getArtistInfo() != null ? source.getArtistInfo().getGenre() : null)
                .withAvatar(imagesService.fullImagePath(source.getAvatar()));
    }
}
