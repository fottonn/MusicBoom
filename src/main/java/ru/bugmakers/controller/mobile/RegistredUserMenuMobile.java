package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.FeedBackRequestMobile;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.entity.FeedBack;
import ru.bugmakers.enums.FeedBackType;
import ru.bugmakers.service.FeedBackService;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/registereduser")
public class RegistredUserMenuMobile extends MbController {

    private FeedBackService feedBackService;

    @Autowired
    public void setFeedBackService(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @PostMapping(value = "/feedback.send")
    public ResponseEntity<MbResponse> feedbackSend(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                   @RequestBody FeedBackRequestMobile rq) {

        MbResponse rs;
        try {
            FeedBack feedBack = new FeedBack();
            feedBack.setUserId(userPrincipal.getUser().getId());
            feedBack.setFeedBackType(FeedBackType.valueOf(rq.getType().toUpperCase()));
            feedBack.setText(rq.getText());
            feedBackService.saveFeedBack(feedBack);
            rs = MbResponse.success();
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }
}
