package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.dto.response.web.ArtistInfoResponseWeb;
import ru.bugmakers.mappers.converters.User2UserDtoConverter;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/webapi/artist")
public class ArtistRoomWeb {

    private User2UserDtoConverter user2UserDtoConverter;

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }

    @PostMapping(value = "/personal")
    public ResponseEntity<MbResponse> artistPersonal(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        ArtistInfoResponseWeb rs;
        try {
            UserDTO userDTO = user2UserDtoConverter.convert(userPrincipal.getUser());
            rs = new ArtistInfoResponseWeb();
            rs.setUser(userDTO);
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

}
