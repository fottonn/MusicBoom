package ru.bugmakers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.enums.RsStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping
    public ResponseEntity<MbResponse> testMethod(HttpServletRequest request) throws IOException, ServletException {
//        String fileName = avatar.getOriginalFilename();
        request.getParts();
        return ResponseEntity.ok(new MbResponse(RsStatus.SUCCESS));
    }


}
