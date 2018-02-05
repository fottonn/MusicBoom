package ru.bugmakers.service;

import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bugmakers.entity.ConfirmEmail;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.utils.SendEmail;

import java.util.UUID;

/**
 * Created by Ayrat on 31.01.2018.
 */
public class EmailConfirmationService {

    private SendEmail sendEmail;
    private UserService userService;
    //TODO Захардкодить домен
    public static final String DOMAIN = "ourDomain";

    @Autowired
    public void setSendEmail(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void confirmEmail(User user) throws MbException {
        UUID generatedValue = Generators.timeBasedGenerator().generate();
        String email = user.getEmail();
        String messageText = "Добрый день " + user.getName() + " " + user.getSurName() + " " + "вы зарегестрировались на сайте " + DOMAIN +
                 " для подтвверждения своего аккаунта пожалуйста перейдите по ссылке: " + DOMAIN + "/registration/" + generatedValue.toString()
                + ". Если вы не знаете о чем речь," +  "просто проигнорируйте это сообщение.";
        if (sendEmail.sendEmail(email, messageText)) {
            ConfirmEmail confirmEmail = new ConfirmEmail();
            confirmEmail.setGeneratedValue(generatedValue.toString());
            user.setConfirmEmail(confirmEmail);
            user.setEnabled(Boolean.FALSE);
            User saveUser = userService.saveUser(user);
            if (saveUser == null) {
                throw MbException.create(MbError.SEE02);
            }
        }else{
            throw MbException.create(MbError.SEE01);
        }
    }
}
