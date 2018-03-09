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
import ru.bugmakers.dto.request.mobile.ListenerProfileMobileRq;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.service.UserService;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/mapi/listener")
public class ListenerProfileMobile extends MbController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/editing.personal")
    public ResponseEntity<MbResponseToMobile> personalEdit(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                           @RequestBody ListenerProfileMobileRq rq) {
        try {
            userService.updateUser(userPrincipal.getUser().withCity(rq.getCity()).withCountry(rq.getCountry()));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/editing.phone")
    public ResponseEntity<MbResponseToMobile> phoneEdit(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                        @RequestBody ListenerProfileMobileRq rq) {

        try {
            userService.updateUser(userPrincipal.getUser().withPhone(rq.getPhone()));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));

    }


    @PostMapping(value = "/card.attach")
    public ResponseEntity<MbResponseToMobile> cardAttach(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                         @RequestBody ListenerProfileMobileRq rq) {

        try {
            userService.updateUser(userPrincipal.getUser().withLinkedCard(Boolean.parseBoolean(rq.getIsAttached())));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));
    }
}
