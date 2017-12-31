package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.web.ArtistFeedbackRequestWeb;
import ru.bugmakers.dto.request.web.UserFeedbackRequestWeb;
import ru.bugmakers.dto.response.web.ArtistFeedbackResponseWeb;
import ru.bugmakers.dto.response.web.ResponseToWeb;
import ru.bugmakers.dto.response.web.UserFeedbackResponseWeb;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/webapi/feedback/")
public class FeedbackWeb extends CommonController {

    @PostMapping(value = "artist")
    public ResponseEntity<ResponseToWeb> artistFeedback(@RequestBody ArtistFeedbackRequestWeb feedBackRequest) {
        ArtistFeedbackResponseWeb artistFeedbackResponseWeb = null;
        return ResponseEntity.ok(artistFeedbackResponseWeb);
    }

    @PostMapping(value = "user")
    public ResponseEntity<ResponseToWeb> userFeedback(@RequestBody UserFeedbackRequestWeb feedBackRequest) {
        UserFeedbackResponseWeb userFeedbackResponseWeb = null;
        return ResponseEntity.ok(userFeedbackResponseWeb);
    }
}

