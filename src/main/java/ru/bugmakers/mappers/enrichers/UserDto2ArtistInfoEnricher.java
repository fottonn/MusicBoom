package ru.bugmakers.mappers.enrichers;

import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.ArtistInfo;

import static java.util.Optional.ofNullable;

/**
 * Created by Ivan
 */
@Component
public class UserDto2ArtistInfoEnricher implements MBEnricher<UserDTO, ArtistInfo> {
    @Override
    public void enrich(UserDTO source, ArtistInfo target) {
        if (source != null) {
            if (target == null) target = new ArtistInfo();
            target.setOrderable(ofNullable(source.isOrderable()).orElse(target.isOrderable()));
            target.setCreativity(ofNullable(source.getCreativity()).orElse(target.getCreativity()));
            target.setGenre(ofNullable(source.getGenre()).orElse(target.getGenre()));
            target.setInstrument(ofNullable(source.getInstrument()).orElse(target.getInstrument()));
        }
    }
}
