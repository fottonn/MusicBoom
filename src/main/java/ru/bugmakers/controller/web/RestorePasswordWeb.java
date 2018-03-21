package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.NewPasswordRequestWeb;
import ru.bugmakers.dto.response.web.ChangePasswordResponseWeb;
import ru.bugmakers.dto.response.web.CodeFromEmailValidationResponseWeb;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;
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
    public ResponseEntity<MbResponseToWeb> restorePassword(@RequestParam("email") String email) {
        MbResponseToWeb rs;
        try {
            emailService.sendPasswordRestoreEmail(email);
            rs = new MbResponseToWeb(RsStatus.SUCCESS);
        } catch (MbException e) {
            rs = new MbResponseToWeb(e, RsStatus.ERROR);
        } catch (Exception e) {
            rs = new MbResponseToWeb(RsStatus.ERROR);
        }
        return ResponseEntity.ok(rs);
    }

    @GetMapping(params = {"email", "id", "code"})
    public ResponseEntity<MbResponseToWeb> chngPassword(@RequestParam("email") String email,
                                                        @RequestParam("code") String code,
                                                        @RequestParam("id") String userId) {
        CodeFromEmailValidationResponseWeb codeFromEmailValidationResponseWeb = null;
        return ResponseEntity.ok(codeFromEmailValidationResponseWeb);
    }

    @PostMapping(value = "/chngpassword")
    public ResponseEntity<MbResponseToWeb> artistProfileEdit(@RequestBody NewPasswordRequestWeb newPasswordRequestWeb) {
        ChangePasswordResponseWeb changePasswordResponseWeb = null;
        return ResponseEntity.ok(changePasswordResponseWeb);
    }
}
