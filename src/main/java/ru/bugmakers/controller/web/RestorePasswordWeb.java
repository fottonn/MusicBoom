package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.NewPasswordRequestWeb;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.EmailService;
import ru.bugmakers.service.UserService;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/restorepassword")
public class RestorePasswordWeb extends MbController {

    private EmailService emailService;
    private UserService userService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(params = "email")
    public ResponseEntity<MbResponse> restorePassword(@RequestParam("email") String email) {
        MbResponse rs;
        try {
            emailService.sendPasswordRestoreEmail(email);
            rs = MbResponse.success();
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

    @PostMapping()
    public ResponseEntity<MbResponse> changePassword(@RequestBody NewPasswordRequestWeb rq) {
        MbResponse rs;

        try {
            if (!rq.getPassword().equals(rq.getConfirmPassword())) {
                throw MbException.create(MbError.PRE02);
            }
            userService.restorePassword(rq.getPassword(), rq.getCode(), rq.getUserId());
            rs = MbResponse.success();
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }

        return ResponseEntity.ok(rs);
    }
}
