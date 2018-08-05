package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.response.MbResponse;
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

    @GetMapping(value = "/email/{code}")
    public ResponseEntity<MbResponse> confirm(@PathVariable("code") String code) {

        MbResponse rs;
        try {
            emailService.checkConfirmationCode(code);
            rs = MbResponse.success();
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }

        return ResponseEntity.ok(rs);
    }
}
