package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.FeedBack;
import ru.bugmakers.dto.ResponseToMobile;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/registereduser/")
public class RegistredUserMenu {
    @RequestMapping(method = RequestMethod.POST, value = "feedback.send")
        public ResponseEntity<ResponseToMobile> feedbackSend(@RequestBody FeedBack feedBack) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "about.app")
    public ResponseEntity<ResponseToMobile> aboutApp() {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "logout")
    public ResponseEntity<ResponseToMobile> logout(@RequestParam("session_id") String sessionId,
                                                       @RequestParam("id") String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
}
