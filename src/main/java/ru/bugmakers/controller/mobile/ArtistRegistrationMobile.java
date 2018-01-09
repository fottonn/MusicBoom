package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.RegistrationAtistRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.service.ArtistRegistrationService;

/**
 * Регистрация музыканта
 * Created by Ayrat on 20.11.2017.
 */
@RestController
@RequestMapping("/mapi/registration/")
public class ArtistRegistrationMobile extends MbController {

    private ArtistRegistrationService artistRegistrationService;

    @Autowired
    public void setArtistRegistrationService(ArtistRegistrationService artistRegistrationService) {
        this.artistRegistrationService = artistRegistrationService;
    }

    @PostMapping(value = "musician")
    public ResponseEntity<MbResponseToMobile> musicianRegistration(@RequestBody RegistrationAtistRequestMobile userRequest) {
        ArtistRegistrationResponse artistRegistrationResponse;
        artistRegistrationResponse = artistRegistrationService.artistRegister(userRequest);

        return ResponseEntity.ok(artistRegistrationResponse);
    }


}
