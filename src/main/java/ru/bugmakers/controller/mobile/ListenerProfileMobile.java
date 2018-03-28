package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.ListenerProfileMobileRq;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.entity.Email;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.ArtistProfileEditService;
import ru.bugmakers.service.EmailService;
import ru.bugmakers.service.UserService;
import ru.bugmakers.utils.MultipartUtils;

import static java.util.Optional.ofNullable;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/mapi/listener/editing")
public class ListenerProfileMobile extends MbController {

    private UserService userService;
    private EmailService emailService;
    private ArtistProfileEditService artistProfileEditService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setArtistProfileEditService(ArtistProfileEditService artistProfileEditService) {
        this.artistProfileEditService = artistProfileEditService;
    }

    @PostMapping(value = "/personal")
    public ResponseEntity<MbResponseToMobile> personalEdit(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                           @RequestBody ListenerProfileMobileRq rq) {
        User user = userPrincipal.getUser();
        try {
            user.setCity(ofNullable(rq.getCity()).orElse(user.getCity()));
            user.setCountry(ofNullable(rq.getCountry()).orElse(user.getCountry()));
            String email = rq.getEmail();
            if (email != null && email.equalsIgnoreCase(user.getEmail() != null ? user.getEmail().getValue() : null)) {
                user.setEmail(new Email(email));
            }
            user = userService.updateUser(user);
            if (user.getEmail() != null && !user.getEmail().isEnabled()) {
                emailService.sendConfirmationEmail(user);
            }
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

    @PostMapping(value = "/avatar.change")
    public ResponseEntity<MbResponseToWeb> changeArtistAvatar(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                              MultipartHttpServletRequest rq) {
        try {
            artistProfileEditService.artistAvatarChange(userPrincipal.getUser(), MultipartUtils.findAvatarPart(rq));
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToWeb(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }


}
