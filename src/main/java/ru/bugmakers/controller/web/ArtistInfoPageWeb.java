package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.response.web.ArtistInfoResponseWeb;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.enums.RsStatus;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi/artist")
public class ArtistInfoPageWeb extends MbController {

    @PostMapping(value = "/info")
    public ResponseEntity<MbResponseToWeb> ArtistWebAuthentication(@RequestParam("artistId") String artistId) {
        ArtistInfoResponseWeb artistInfoResponseWeb = new ArtistInfoResponseWeb(RsStatus.SUCCESS);
        return ResponseEntity.ok(artistInfoResponseWeb);
    }
}
