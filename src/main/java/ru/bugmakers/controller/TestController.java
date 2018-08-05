package ru.bugmakers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.entity.Email;
import ru.bugmakers.entity.User;
import ru.bugmakers.service.EmailService;
import ru.bugmakers.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<MbResponse> testMethod(@RequestParam("email") String email,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("surname") String surname) {
        User user = new User();
        user
                .withName(name)
                .withSurName(surname)
                .withBirthDay(LocalDate.of(1986, Month.OCTOBER, 24))
                .withRegistrationDate(LocalDateTime.now())
                .withCountry("Russia")
                .withEmail(new Email(email));
        try {
            emailService.sendEmailToArtist(user, "Hello, " + name + "!!! Халляр ничек?", "Хеллер белешу темасы");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(MbResponse.success());
    }


}
