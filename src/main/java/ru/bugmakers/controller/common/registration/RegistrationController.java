package ru.bugmakers.controller.common.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.RegistrationRequest;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.dto.response.RegistrationResponse;
import ru.bugmakers.enums.SocialProvider;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.validator.common.RegistrationRequestValidator;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController extends MbController {

    private RegistrationRequestValidator registrationRequestValidator;
    private RegistratorCreator registratorCreator;

    @Autowired
    public void setRegistrationRequestValidator(RegistrationRequestValidator registrationRequestValidator) {
        this.registrationRequestValidator = registrationRequestValidator;
    }

    @Autowired
    public void setRegistratorCreator(RegistratorCreator registratorCreator) {
        this.registratorCreator = registratorCreator;
    }

    @PostMapping(params = "user_type")
    public ResponseEntity<MbResponse> register(@RequestBody RegistrationRequest rq,
                                               @RequestParam("user_type") String userType) {
        RegistrationResponse rs;
        try {
            registrationRequestValidator.validate(rq);
            SocialProvider provider;
            try {
                provider = SocialProvider.valueOf(rq.getProvider().toUpperCase());
            } catch (Exception e) {
                provider = null;
            }
            Registrator registrator = registratorCreator.getRegistrator(provider);
            UserDTO user = registrator.register(UserType.valueOf(userType.toUpperCase()), rq.getUser(), rq.getSocialId(), rq.getToken());
            rs = new RegistrationResponse();
            rs.setUser(user);
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

    //Не используем
    @Deprecated
    @PostMapping(params = {"provider", "user_type"})
    public ResponseEntity<MbResponse> socialRegister(@RequestBody RegistrationRequest rq,
                                                     @RequestParam("user_type") String userType,
                                                     @RequestParam("provider") String provider) {
        RegistrationResponse rs;
        try {
            registrationRequestValidator.validate(rq);
            Registrator registrator = registratorCreator.getRegistrator(SocialProvider.valueOf(provider.toUpperCase()));
            UserDTO user = registrator.register(UserType.valueOf(userType.toUpperCase()), rq.getUser(), rq.getSocialId(), rq.getToken());
            rs = new RegistrationResponse();
            rs.setUser(user);
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(rs);
    }

}
