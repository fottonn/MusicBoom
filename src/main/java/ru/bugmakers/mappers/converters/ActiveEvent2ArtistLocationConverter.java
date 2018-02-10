package ru.bugmakers.mappers.converters;

import org.springframework.stereotype.Component;
import ru.bugmakers.dto.ArtistsLocation;
import ru.bugmakers.localpers.entity.ActiveEvent;

/**
 * Created by Ivan
 */
@Component
public class ActiveEvent2ArtistLocationConverter implements MbConverter<ActiveEvent, ArtistsLocation> {
    @Override
    public ArtistsLocation convert(ActiveEvent source) {
        return new ArtistsLocation(
                String.valueOf(source.getUserId()),
                String.valueOf(source.getLng()),
                String.valueOf(source.getLat()));
    }
}
