package ru.bugmakers.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.response.mobile.FindArtistResponseMobile;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/mapi")
public class ListenerMainPageMobile extends MbController {

    @GetMapping(value = "/findArtist")
    public ResponseEntity<MbResponseToMobile> findArtist(@RequestParam("session_id") String sessionId,
                                                         @RequestParam("id") String id,
                                                         @RequestParam("alias_text") String aliasText) {
        FindArtistResponseMobile findArtistResponseMobile = null;
        return ResponseEntity.ok(findArtistResponseMobile);
    }

}
