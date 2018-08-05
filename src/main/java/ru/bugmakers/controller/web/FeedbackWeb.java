package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.FeedbackWebRq;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.entity.FeedBack;
import ru.bugmakers.enums.FeedBackType;
import ru.bugmakers.service.FeedBackService;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/webapi/feedback")
public class FeedbackWeb extends MbController {

    private FeedBackService feedBackService;

    @Autowired
    public void setFeedBackService(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @PostMapping(value = "/artist")
    public ResponseEntity<MbResponse> artistFeedback(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                     @RequestBody FeedbackWebRq rq) {
        MbResponse rs;
        try {
            Long userId = userPrincipal.getUser().getId();
            FeedBackType feedBackType = FeedBackType.valueOf(rq.getType().toUpperCase());
            feedBackService.saveFeedBack(new FeedBack(userId, feedBackType, rq.getText()));
            rs = MbResponse.success();
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);

    }

    @PostMapping(value = "/user")
    public ResponseEntity<MbResponse> userFeedback(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                   @RequestBody FeedbackWebRq rq) {
        MbResponse rs;
        try {
            Long userId = userPrincipal.getUser().getId();
            FeedBackType feedBackType = FeedBackType.PROPOSAL;
            feedBackService.saveFeedBack(new FeedBack(userId, feedBackType, rq.getText()));
            rs = MbResponse.success();
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }
}

