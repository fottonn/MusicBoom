package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.EmailService;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/confirmation")
public class EmailConfirmWeb extends MbController {

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping(value = "/email", params = {"email", "code"})
    public ResponseEntity<MbResponseToWeb> confirm(@RequestParam("email") String email,
                                                   @RequestParam("code") String code) {

        MbResponseToWeb rs;
        try {
            emailService.checkConfirmationCode(email, code);
            rs = new MbResponseToWeb(RsStatus.SUCCESS);
        } catch (MbException e) {
            rs = new MbResponseToWeb(e, RsStatus.ERROR);
        } catch (Exception e) {
            rs = new MbResponseToWeb(RsStatus.ERROR);
        }
        return ResponseEntity.ok(rs);
    }
}
