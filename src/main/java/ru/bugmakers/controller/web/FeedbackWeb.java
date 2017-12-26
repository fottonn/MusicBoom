package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.request.web.ArtistFeedbackRequest;
import ru.bugmakers.dto.request.web.UserFeedbackRequest;
import ru.bugmakers.dto.response.web.ArtistFeedbackResponseWeb;
import ru.bugmakers.dto.response.web.ResponseToWeb;
import ru.bugmakers.dto.response.web.UserFeedbackResponseWeb;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/webapi/feedback/")
public class FeedbackWeb {
    @RequestMapping(method = RequestMethod.POST, value = "artist")
    public ResponseEntity<ResponseToWeb> artistFeedback(@RequestBody ArtistFeedbackRequest feedBackRequest) {
        ArtistFeedbackResponseWeb artistFeedbackResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistFeedbackResponseWeb, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "user")
    public ResponseEntity<ResponseToWeb> userFeedback(@RequestBody UserFeedbackRequest feedBackRequest) {
        UserFeedbackResponseWeb userFeedbackResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(userFeedbackResponseWeb, responseHeaders, HttpStatus.OK);
    }
}

