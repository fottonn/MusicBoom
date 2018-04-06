package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.NewPasswordRequestWeb;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.dto.response.web.ChangePasswordResponseWeb;
import ru.bugmakers.dto.response.web.CodeFromEmailValidationResponseWeb;
import ru.bugmakers.service.EmailService;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/restorepassword")
public class RestorePasswordWeb extends MbController {

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
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

    @GetMapping(params = {"email", "id", "code"})
    public ResponseEntity<MbResponse> chngPassword(@RequestParam("email") String email,
                                                   @RequestParam("code") String code,
                                                   @RequestParam("id") String userId) {
        CodeFromEmailValidationResponseWeb codeFromEmailValidationResponseWeb = null;
        return ResponseEntity.ok(codeFromEmailValidationResponseWeb);
    }

    @PostMapping(value = "/chngpassword")
    public ResponseEntity<MbResponse> artistProfileEdit(@RequestBody NewPasswordRequestWeb newPasswordRequestWeb) {
        ChangePasswordResponseWeb changePasswordResponseWeb = null;
        return ResponseEntity.ok(changePasswordResponseWeb);
    }
}
