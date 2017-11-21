package ru.bugmakers.controller.mobile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseToMobile musicianRegistration() {
        return null;
    }

}
