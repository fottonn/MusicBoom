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
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.entity.FeedBack;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.FeedBackType;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.service.FeedBackService;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/registereduser/")
public class RegistredUserMenuMobile extends MbController {

    private FeedBackService feedBackService;

    @Autowired
    public void setFeedBackService(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @PostMapping(value = "feedback.send")
    public ResponseEntity<MbResponseToMobile> feedbackSend(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                           @RequestBody FeedBackRequestMobile rq) {

        MbResponseToMobile rs;

        try {

            User user = userPrincipal.getUser();
            FeedBack feedBack = new FeedBack();
            feedBack.setUser(user);
            feedBack.setFeedBackType(FeedBackType.valueOf(rq.getType().toUpperCase()));
            feedBack.setText(rq.getText());
            feedBackService.saveFeedBack(feedBack);
            rs = new MbResponseToMobile(RsStatus.SUCCESS);

        } catch (Exception e) {
            rs = new MbResponseToMobile(RsStatus.ERROR);
        }

        return ResponseEntity.ok(rs);
    }
}
