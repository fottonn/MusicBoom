package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.mobile.RegistrationArtistRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.ArtistRegistrationService;
import ru.bugmakers.validator.ArtistRegistrationMobileValidator;

/**
 * Регистрация музыканта
 * Created by Ayrat on 20.11.2017.
 */
@RestController
@RequestMapping("/registration/mapi/artist")
public class ArtistRegistrationMobile extends MbController {

    private ArtistRegistrationService artistRegistrationService;
    private ArtistRegistrationMobileValidator artistRegistrationMobileValidator;

    @Autowired
    public void setArtistRegistrationService(ArtistRegistrationService artistRegistrationService) {
        this.artistRegistrationService = artistRegistrationService;
    }
    @Autowired
    public void setArtistRegistrationMobileValidator(ArtistRegistrationMobileValidator artistRegistrationMobileValidator) {
        this.artistRegistrationMobileValidator = artistRegistrationMobileValidator;
    }

    @PostMapping
    public ResponseEntity<MbResponseToMobile> artistRegistration(@RequestBody RegistrationArtistRequestMobile userRequest) {
        ArtistRegistrationResponse artistRegistrationResponse;
        try {
            artistRegistrationMobileValidator.validate(userRequest);
            UserDTO userDtoRq = userRequest.getUser();
            UserDTO userDtoRs = artistRegistrationService.artistRegister(userDtoRq);
            Assert.notNull(userDtoRs, "Ошибка аутентификации");
            artistRegistrationResponse = new ArtistRegistrationResponse(RsStatus.SUCCESS);
            artistRegistrationResponse.setUser(userDtoRs);
        }catch (MbException e){
            artistRegistrationResponse = new ArtistRegistrationResponse(e, RsStatus.ERROR);
        }catch (Exception e) {
            artistRegistrationResponse = new ArtistRegistrationResponse(MbException.create(MbError.RGE08), RsStatus.ERROR);
        }
        return ResponseEntity.ok(artistRegistrationResponse);
    }
}
