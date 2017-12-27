package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.mobile.RegistrationArtistRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;
import ru.bugmakers.service.ArtistRegistrationService;

/**
 * Регистрация музыканта
 * Created by Ayrat on 20.11.2017.
 */
@RestController
@RequestMapping("/mapi/registration/")
public class ArtistRegistrationMobile extends CommonController {

    private ArtistRegistrationService artistRegistrationService;
    @Autowired
    public void setArtistRegistrationService(ArtistRegistrationService artistRegistrationService) {
        this.artistRegistrationService = artistRegistrationService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "musician")
    public ResponseEntity<ResponseToMobile> musicianRegistration(@RequestBody RegistrationArtistRequestMobile userRequest) {
        ArtistRegistrationResponse artistRegistrationResponse;
        artistRegistrationResponse = artistRegistrationService.artistRegister(userRequest);
        return ResponseEntity.ok().headers(responseHeaders).body(artistRegistrationResponse);
    }


}
