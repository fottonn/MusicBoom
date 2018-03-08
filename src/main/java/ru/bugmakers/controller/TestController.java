package ru.bugmakers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @PostMapping
    public ResponseEntity<MbResponse> testMethod(HttpServletRequest request) throws IOException, ServletException {
        User user = new User();
        user
                .withName("Ayrat")
                .withSurName("Tagirov")
                .withBirthDay(LocalDate.of(1986, Month.OCTOBER, 24))
                .withRegistrationDate(LocalDateTime.now())
                .withCountry("Russia");
        userService.saveUser(user);
        return ResponseEntity.ok(new MbResponse(RsStatus.SUCCESS));
    }


}
