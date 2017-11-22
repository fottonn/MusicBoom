package ru.bugmakers.controller.mobile;

import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.ArtistEditRequest;
import ru.bugmakers.dto.RegistrationListenerRequest;
import ru.bugmakers.dto.RegistrationMusicianRequest;
import ru.bugmakers.dto.ResponseToMobile;

/**
 * Created by Ayrat on 20.11.2017.
 */
@RestController
@RequestMapping("/mapi")
public class MobileController {
    @RequestMapping(method = RequestMethod.GET, value = "/authentification", params = {"login", "hash_password"})
    public ResponseToMobile authentification(@RequestParam("login") String login, @RequestParam("hash_password") String hashPassword ){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "registration/musician")
    public ResponseToMobile musicianRegistration(@RequestBody RegistrationMusicianRequest userRequest) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "registration/listener")
    public ResponseToMobile listenerRegistration(@RequestBody RegistrationListenerRequest userRequest) {
        return null;
    }







}
