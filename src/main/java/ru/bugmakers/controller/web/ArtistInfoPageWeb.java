package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.dto.response.web.ArtistInfoResponseWeb;
import ru.bugmakers.dto.response.web.ArtistListWebRs;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.converters.User2UserDtoPublicConverter;
import ru.bugmakers.service.UserService;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/")
public class ArtistInfoPageWeb extends MbController {

    private UserService userService;
    private User2UserDtoPublicConverter user2UserDtoPublicConverter;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUser2UserDtoPublicConverter(User2UserDtoPublicConverter user2UserDtoPublicConverter) {
        this.user2UserDtoPublicConverter = user2UserDtoPublicConverter;
    }

    @GetMapping(value = "artist/{id}")
    public ResponseEntity<MbResponse> artistById(@PathVariable("id") String id) {
        ArtistInfoResponseWeb rs;
        try {
            User userById = userService.findUserById(Long.parseLong(id));
            if (userById == null) throw MbException.create(MbError.CME08);
            UserDTO userDTO = user2UserDtoPublicConverter.convert(userById);
            rs = new ArtistInfoResponseWeb();
            rs.setUser(userDTO);
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

    @GetMapping(value = "artists", params = {"city", "page", "size"})
    public ResponseEntity<MbResponse> artistsByCity(@RequestParam("city") String city,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("size") int size) {
        ArtistListWebRs rs;
        try {
            Page<User> users = userService.findAllUsersByCity(city, PageRequest.of(page - 1, size, Sort.by("id")));
            Page<UserDTO> artistsPage = users.map(user -> user2UserDtoPublicConverter.convert(user));
            rs = new ArtistListWebRs();
            rs.setArtists(artistsPage.getContent());
            rs.setPage(artistsPage.getNumber() + 1);
            rs.setPageSize(artistsPage.getSize());
            rs.setArtistCountInPage(artistsPage.getNumberOfElements());
            rs.setTotalPages(artistsPage.getTotalPages());
            rs.setTotalArtists(artistsPage.getTotalElements());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }
}
