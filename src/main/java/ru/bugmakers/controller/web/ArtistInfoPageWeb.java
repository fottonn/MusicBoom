package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.response.web.ArtistInfoResponseWeb;
import ru.bugmakers.dto.response.web.ResponseToWeb;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi/")
public class ArtistInfoPageWeb extends CommonController {

    @GetMapping(value = "getArtistInfo")
    public ResponseEntity<ResponseToWeb> ArtistWebAuthentication(@RequestParam("artistId") String artistId) {
        ArtistInfoResponseWeb artistInfoResponseWeb = null;
        return ResponseEntity.ok(artistInfoResponseWeb);
    }
}
