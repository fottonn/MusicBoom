package ru.bugmakers.controller.common.enter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.dto.response.AuthenticationResponse;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.UserAuthenticationService;

@RestController
@RequestMapping(value = "/enter")
public class EnterController {

    private UserAuthenticationService userAuthenticationService;

    @Autowired
    public void setUserAuthenticationService(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping
    public ResponseEntity<MbResponse> enterWithToken(@AuthenticationPrincipal UserPrincipal principal) {
        //если запрос добрался до этого места, значит аутентификация в TokenAuthenticationCheckFilter прошла успешно
        AuthenticationResponse rs = null;
        try {
            if (principal != null) {
                User currentUser = principal.getUser();
                if (currentUser != null) rs = userAuthenticationService.getResponseByUserType(currentUser.getId());
            }
            if (rs == null) throw MbException.create(MbError.AUE08);
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

}
