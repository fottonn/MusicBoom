package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.response.web.ResponseToWeb;

/**
 * Created by Ayrat on 05.12.2017.
 */
//TODO это не точно, нужно узнать как это делается, используя ВК и прочие сети
@RestController
@RequestMapping("/webapi/")
public class ArtistAuthenticationWeb {

    @RequestMapping(method = RequestMethod.GET, value = "authentication")
    public ResponseEntity<ResponseToWeb> ArtistWebAuthentication(@RequestParam("id") String id,
                                                                 @RequestParam("hash_password") String passwordHash) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }

}
