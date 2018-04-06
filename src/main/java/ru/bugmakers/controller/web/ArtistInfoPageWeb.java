package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.dto.response.web.ArtistInfoResponseWeb;
import ru.bugmakers.mappers.converters.User2UserDtoConverter;
import ru.bugmakers.service.UserService;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi/artist")
public class ArtistInfoPageWeb extends MbController {

    private UserService userService;
    private User2UserDtoConverter user2UserDtoConverter;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<MbResponse> ArtistWebAuthentication(@PathVariable("id") String id) {
        ArtistInfoResponseWeb rs;
        try {
            UserDTO userDTO = user2UserDtoConverter.convert(userService.findUserById(Long.parseLong(id)));
            rs = new ArtistInfoResponseWeb();
            rs.setUser(userDTO);
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }
}
