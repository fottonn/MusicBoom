package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.web.ArtistRegistrationRequestWeb;
import ru.bugmakers.dto.response.web.ArtistRegistrationResponseWeb;
import ru.bugmakers.dto.response.web.ResponseToWeb;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/webapi/artist/")
public class ArtistRegistrationWeb extends CommonController {

    @PostMapping(value = "registration")
    public ResponseEntity<ResponseToWeb> artistRegistration(@RequestBody ArtistRegistrationRequestWeb artistProfileEditing) {
        ArtistRegistrationResponseWeb artistRegistrationResponseWeb = null;
        return ResponseEntity.ok(artistRegistrationResponseWeb);
    }
}
