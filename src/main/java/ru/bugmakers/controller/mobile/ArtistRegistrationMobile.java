package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.request.mobile.RegistrationMusicianRequest;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;

/**
 * Регистрация музыканта
 * Created by Ayrat on 20.11.2017.
 */
@RestController
@RequestMapping("/mapi/registration")
public class ArtistRegistrationMobile {
    @RequestMapping(method = RequestMethod.POST, value = "/musician")
    public ResponseEntity<ResponseToMobile> musicianRegistration(@RequestBody RegistrationMusicianRequest userRequest) {
        ArtistRegistrationResponse artistRegistrationResponse = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(artistRegistrationResponse, responseHeaders, HttpStatus.OK);
    }


}
