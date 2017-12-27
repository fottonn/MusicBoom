package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.web.NewPasswordRequestWeb;
import ru.bugmakers.dto.response.web.ChangePasswordResponseWeb;
import ru.bugmakers.dto.response.web.CodeFromEmailValidationResponseWeb;
import ru.bugmakers.dto.response.web.ResponseToWeb;
import ru.bugmakers.dto.response.web.RestorePasswordResponseWeb;

/**
 * Created by Ayrat on 05.12.2017.
 */
@RestController
@RequestMapping("/webapi/")
public class RestorePasswordWeb extends CommonController{
    @RequestMapping(method = RequestMethod.GET, value = "restorepassword")
    public ResponseEntity<ResponseToWeb> restorePassword(@RequestParam("email") String email) {
        RestorePasswordResponseWeb restorePasswordResponseWeb = null;
        return ResponseEntity.ok().headers(responseHeaders).body(restorePasswordResponseWeb);
    }

    @RequestMapping(method = RequestMethod.GET, value = "codefromemail")
    public ResponseEntity<ResponseToWeb> chngPassword(@RequestParam("code") String code,
                                                      @RequestParam("user_id") String userId) {
        CodeFromEmailValidationResponseWeb codeFromEmailValidationResponseWeb = null;
        return ResponseEntity.ok().headers(responseHeaders).body(codeFromEmailValidationResponseWeb);
    }
    @RequestMapping(method = RequestMethod.POST, value = "chngpassword")
    public ResponseEntity<ResponseToWeb> artistProfileEdit(@RequestBody NewPasswordRequestWeb newPasswordRequestWeb) {
        ChangePasswordResponseWeb changePasswordResponseWeb = null;
        return ResponseEntity.ok().headers(responseHeaders).body(changePasswordResponseWeb);
    }
}
