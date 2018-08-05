package ru.bugmakers.mappers.converters;

import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.ArtistInfo;

import java.util.Optional;

/**
 * Created by Ivan
 */
@Component
public class UserDto2ArtistInfoConverter implements MbConverter<UserDTO, ArtistInfo> {
    @Override
    public ArtistInfo convert(UserDTO source) {
        if (source == null) return null;
        ArtistInfo artistInfo = new ArtistInfo();
        artistInfo.setOrderable(Optional.ofNullable(source.isOrderable()).orElse(false));
        artistInfo.setGenre(source.getGenre());
        artistInfo.setCreativity(source.getCreativity());
        artistInfo.setInstrument(source.getInstrument());
        return artistInfo;
    }
}
