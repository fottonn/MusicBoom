package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.NewPasswordRequestWeb;
import ru.bugmakers.dto.response.web.ChangePasswordResponseWeb;
import ru.bugmakers.dto.response.web.CodeFromEmailValidationResponseWeb;
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.dto.response.web.RestorePasswordResponseWeb;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/webapi/")
public class RestorePasswordWeb extends MbController {

    @GetMapping(value = "restorepassword")
    public ResponseEntity<MbResponseToWeb> restorePassword(@RequestParam("email") String email) {
        RestorePasswordResponseWeb restorePasswordResponseWeb = null;
        return ResponseEntity.ok(restorePasswordResponseWeb);
    }

    @GetMapping(value = "codefromemail")
    public ResponseEntity<MbResponseToWeb> chngPassword(@RequestParam("code") String code,
                                                        @RequestParam("user_id") String userId) {
        CodeFromEmailValidationResponseWeb codeFromEmailValidationResponseWeb = null;
        return ResponseEntity.ok(codeFromEmailValidationResponseWeb);
    }

    @PostMapping(value = "chngpassword")
    public ResponseEntity<MbResponseToWeb> artistProfileEdit(@RequestBody NewPasswordRequestWeb newPasswordRequestWeb) {
        ChangePasswordResponseWeb changePasswordResponseWeb = null;
        return ResponseEntity.ok(changePasswordResponseWeb);
    }
}
