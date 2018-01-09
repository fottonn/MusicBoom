package ru.bugmakers.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.RegistrationListenerRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;

/**
 * Created by Ayrat on 08.12.2017.
 */
@RestController
@RequestMapping("/mapi/")
public class ListenerRegistartionMobile extends MbController {

    @PostMapping(value = "listener")
    public ResponseEntity<MbResponseToMobile> listenerRegistration(@RequestBody RegistrationListenerRequestMobile userRequest) {
        ArtistRegistrationResponse artistRegistrationResponse = null;
        return ResponseEntity.ok(artistRegistrationResponse);
    }
}
