package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(method = RequestMethod.POST, value = "registration")
    public ResponseEntity<ResponseToWeb> artistRegistration(@RequestBody ArtistRegistrationRequestWeb artistProfileEditing) {
        ArtistRegistrationResponseWeb artistRegistrationResponseWeb = null;
        return ResponseEntity.ok().headers(responseHeaders).body(artistRegistrationResponseWeb);
    }
}
