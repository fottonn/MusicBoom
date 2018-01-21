package ru.bugmakers.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.response.AuthenticationResponse;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.UserAuthenticationService;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController extends MbController {

    private UserAuthenticationService userAuthenticationService;

    @Autowired
    public void setUserAuthenticationService(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@AuthenticationPrincipal UserPrincipal principal) {

        AuthenticationResponse response = null;

        if (principal != null) {
            User currentUser = principal.getUser();
            if (currentUser != null) response = userAuthenticationService.getResponseByUserType(currentUser.getId());
        }
        if (response == null) response = new AuthenticationResponse(MbException.create(MbError.AUE08), RsStatus.ERROR);
        return ResponseEntity.ok(response);
    }
}
