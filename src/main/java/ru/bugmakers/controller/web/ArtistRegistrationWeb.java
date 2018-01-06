package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.ArtistRegistrationRequestWeb;
import ru.bugmakers.dto.response.web.ArtistRegistrationResponseWeb;
import ru.bugmakers.dto.response.web.MbResponseToWeb;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/webapi/artist/")
public class ArtistRegistrationWeb extends MbController {

    @PostMapping(value = "registration")
    public ResponseEntity<MbResponseToWeb> artistRegistration(@RequestBody ArtistRegistrationRequestWeb artistProfileEditing) {
        ArtistRegistrationResponseWeb artistRegistrationResponseWeb = null;
        return ResponseEntity.ok(artistRegistrationResponseWeb);
    }
}
