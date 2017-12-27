package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.request.mobile.FeedBackRequest;
import ru.bugmakers.dto.response.FeedBackResponse;
import ru.bugmakers.dto.response.mobile.AboutAppResponseMobile;
import ru.bugmakers.dto.response.mobile.LogoutResponseMobile;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/registereduser/")
public class RegistredUserMenuMobile {
    @RequestMapping(method = RequestMethod.POST, value = "feedback.send")
        public ResponseEntity<ResponseToMobile> feedbackSend(@RequestBody FeedBackRequest feedBackRequest) {
        FeedBackResponse feedBackResponse = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(feedBackResponse, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "about.app")
    public ResponseEntity<ResponseToMobile> aboutApp() {
        AboutAppResponseMobile aboutAppResponseMobile = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(aboutAppResponseMobile, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "logout")
    public ResponseEntity<ResponseToMobile> logout(@RequestParam("session_id") String sessionId,
                                                       @RequestParam("id") String id) {
        LogoutResponseMobile logoutResponseMobile = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(logoutResponseMobile, responseHeaders, HttpStatus.OK);
    }
}
