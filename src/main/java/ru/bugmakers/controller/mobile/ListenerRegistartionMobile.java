package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.mobile.RegistrationListenerRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;

/**
 * Created by Ayrat on 08.12.2017.
 */
@RestController
@RequestMapping("/mapi/")
public class ListenerRegistartionMobile extends CommonController{

    @RequestMapping(method = RequestMethod.POST, value = "listener")
    public ResponseEntity<ResponseToMobile> listenerRegistration(@RequestBody RegistrationListenerRequestMobile userRequest) {
        ArtistRegistrationResponse artistRegistrationResponse = null;
        return ResponseEntity.ok(artistRegistrationResponse);
    }
}
