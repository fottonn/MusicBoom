package ru.bugmakers.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.controller.common.authentication.Authenticator;
import ru.bugmakers.controller.common.authentication.AuthenticatorFactory;
import ru.bugmakers.dto.common.UserDTO;
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
    private

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

    @GetMapping(params = {"token", "provider", "social_id"})
    public ResponseEntity<AuthenticationResponse> socialAuthenticate(@RequestParam("token") String token,
                                                                     @RequestParam("provider") String provider,
                                                                     @RequestParam("social_id") String id) {
        AuthenticationResponse response;
        Authenticator authenticator = AuthenticatorFactory.getAuthenticator(provider);

        try {
            UserDTO user = authenticator.authenticate(token, id);
            response = new AuthenticationResponse(RsStatus.SUCCESS);
            response.setUser(user);
        } catch (MbException e) {
            response = new AuthenticationResponse(e, RsStatus.ERROR);
        } catch (Exception e) {
            response = new AuthenticationResponse(MbException.create(MbError.AUE07), RsStatus.ERROR);
        }


        return ResponseEntity.ok(response);
    }
}
