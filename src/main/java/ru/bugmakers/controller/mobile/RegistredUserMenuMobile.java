package ru.bugmakers.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.mobile.FeedBackRequestMobile;
import ru.bugmakers.dto.response.FeedBackResponse;
import ru.bugmakers.dto.response.mobile.AboutAppResponseMobile;
import ru.bugmakers.dto.response.mobile.LogoutResponseMobile;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/registereduser/")
public class RegistredUserMenuMobile extends CommonController {

    @RequestMapping(method = RequestMethod.POST, value = "feedback.send")
    public ResponseEntity<ResponseToMobile> feedbackSend(@RequestBody FeedBackRequestMobile feedBackRequestMobile) {
        FeedBackResponse feedBackResponse = null;
        return ResponseEntity.ok(feedBackResponse);
    }

    @RequestMapping(method = RequestMethod.GET, value = "about.app")
    public ResponseEntity<ResponseToMobile> aboutApp() {
        AboutAppResponseMobile aboutAppResponseMobile = null;
        return ResponseEntity.ok(aboutAppResponseMobile);
    }

    @RequestMapping(method = RequestMethod.GET, value = "logout")
    public ResponseEntity<ResponseToMobile> logout(@RequestParam("session_id") String sessionId,
                                                   @RequestParam("id") String id) {
        LogoutResponseMobile logoutResponseMobile = null;
        return ResponseEntity.ok(logoutResponseMobile);
    }
}
