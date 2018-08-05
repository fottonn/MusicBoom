package ru.bugmakers.mappers.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.ArtistsLocation;
import ru.bugmakers.localpers.entity.ActiveEvent;
import ru.bugmakers.service.ImagesService;
import ru.bugmakers.service.UserService;

/**
 * Created by Ivan
 */
@Component
public class ActiveEvent2ArtistLocationConverter implements MbConverter<ActiveEvent, ArtistsLocation> {

    private UserService userService;
    private ImagesService imagesService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setImagesService(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @Override
    public ArtistsLocation convert(ActiveEvent source) {
        return new ArtistsLocation(
                String.valueOf(source.getUserId()),
                String.valueOf(source.getLng()),
                String.valueOf(source.getLat()),
                imagesService.fullImagePath(userService.findUserById(source.getUserId()).getAvatar()));
    }
}
