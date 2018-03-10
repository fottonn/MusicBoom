package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.mobile.ListenerMainPageMobileRq;
import ru.bugmakers.dto.response.mobile.FindArtistResponseMobile;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.service.UserService;

import java.util.List;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/mapi")
public class ListenerMainPageMobile extends MbController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/find.artist")
    public ResponseEntity<MbResponseToMobile> findArtist(@RequestBody ListenerMainPageMobileRq rq) {
        FindArtistResponseMobile findArtistResponseMobile;
        try {
            List<UserDTO> artists = userService.findAllUsersByNicknameLikeValue(rq.getArtistAlias());
            findArtistResponseMobile = new FindArtistResponseMobile(RsStatus.SUCCESS);
            findArtistResponseMobile.setArtists(artists);
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(findArtistResponseMobile);
    }

}
