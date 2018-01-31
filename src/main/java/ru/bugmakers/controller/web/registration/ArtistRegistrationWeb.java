package ru.bugmakers.controller.web.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.web.ArtistRegistrationRequestWeb;
import ru.bugmakers.dto.response.web.ArtistAuthenticationResponseWeb;
import ru.bugmakers.dto.response.web.ArtistRegistrationResponseWeb;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.enums.SocialProvider;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.ArtistRegistrationService;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/registration/webapi/artist")
public class ArtistRegistrationWeb extends MbController {

    private ArtistRegistrationService artistRegistrationService;

    @Autowired
    public void setArtistRegistrationService(ArtistRegistrationService artistRegistrationService) {
        this.artistRegistrationService = artistRegistrationService;
    }

    @PostMapping
    public ResponseEntity<MbResponseToWeb> artistRegistration(@RequestBody ArtistRegistrationRequestWeb artistProfileEditing) {
        ArtistRegistrationResponseWeb artistRegistrationResponseWeb = null;
        return ResponseEntity.ok(artistRegistrationResponseWeb);
    }

    @PostMapping(value = "/vk")
    public ResponseEntity<MbResponseToWeb> vkArtistRegistration(@RequestBody ArtistRegistrationRequestWeb request) {
        ArtistAuthenticationResponseWeb artistAuthenticationResponseWeb;
        try {
            UserDTO userDtoRs = artistRegistrationService.artistSocialRegister(request.getUser(), SocialProvider.VK);
            artistAuthenticationResponseWeb = new ArtistAuthenticationResponseWeb(RsStatus.SUCCESS);
            artistAuthenticationResponseWeb.setUser(userDtoRs);
        } catch (MbException e) {
            artistAuthenticationResponseWeb = new ArtistAuthenticationResponseWeb(e, RsStatus.ERROR);
        } catch (Exception e) {
            artistAuthenticationResponseWeb = new ArtistAuthenticationResponseWeb(MbException.create(MbError.AUE08), RsStatus.ERROR);
        }
        return ResponseEntity.ok(artistAuthenticationResponseWeb);
    }

    @PostMapping("/fb")
    public ResponseEntity<MbResponseToWeb> fbArtistRegistration(@RequestBody ArtistRegistrationRequestWeb request) {
        ArtistAuthenticationResponseWeb artistAuthenticationResponseWeb;
        try {
            UserDTO userDtoRs = artistRegistrationService.artistSocialRegister(request.getUser(), SocialProvider.FB);
            artistAuthenticationResponseWeb = new ArtistAuthenticationResponseWeb(RsStatus.SUCCESS);
            artistAuthenticationResponseWeb.setUser(userDtoRs);
        } catch (MbException e) {
            artistAuthenticationResponseWeb = new ArtistAuthenticationResponseWeb(e, RsStatus.ERROR);
        } catch (Exception e) {
            artistAuthenticationResponseWeb = new ArtistAuthenticationResponseWeb(MbException.create(MbError.AUE08), RsStatus.ERROR);
        }
        return ResponseEntity.ok(artistAuthenticationResponseWeb);
    }

    @PostMapping(value = "/google")
    public ResponseEntity<MbResponseToWeb> googleArtistRegistration(@RequestBody ArtistRegistrationRequestWeb request) {
        ArtistAuthenticationResponseWeb artistAuthenticationResponseWeb;
        try {
            UserDTO userDtoRs = artistRegistrationService.artistSocialRegister(request.getUser(), SocialProvider.GOOGLE);
            artistAuthenticationResponseWeb = new ArtistAuthenticationResponseWeb(RsStatus.SUCCESS);
            artistAuthenticationResponseWeb.setUser(userDtoRs);
        } catch (MbException e) {
            artistAuthenticationResponseWeb = new ArtistAuthenticationResponseWeb(e, RsStatus.ERROR);
        } catch (Exception e) {
            artistAuthenticationResponseWeb = new ArtistAuthenticationResponseWeb(MbException.create(MbError.AUE08), RsStatus.ERROR);
        }
        return ResponseEntity.ok(artistAuthenticationResponseWeb);
    }
}
