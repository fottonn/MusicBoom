package ru.bugmakers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.utils.UuidGenerator;
import ru.bugmakers.utils.email.EmailSender;
import ru.bugmakers.utils.email.EmailTextBuilder;

/**
 * Created by Ayrat on 31.01.2018.
 */
@Component
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private static final String CONFIRMATION_SUBJECT = "Подтверждение email";
    private static final String DOMAIN = "https://www.musboom.ru/webapi/email/confirmation/";

    private EmailSender emailSender;
    private UserService userService;

    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод валидации email
     *
     * @param user пользователь
     * @throws MbException
     */

    public void sendConfirmationEmail(User user) throws MbException {
        String generatedValue = UuidGenerator.timeBasedUuidGenerate();
        user.getEmail().setConfirmationCode(generatedValue);
        try {
            user = userService.saveUser(user);
        } catch (Exception e) {
            throw MbException.create(MbError.SEE02);
        }
        String email = user.getEmail().getValue();
        String confirmLink = DOMAIN + generatedValue;
        String messageText = EmailTextBuilder.confirmBuild(user.getName(), user.getSurName(), confirmLink);
        emailSender.send(email, CONFIRMATION_SUBJECT, messageText);
    }

    public void sendEmailToAllArtists(String message, String subject) {

        int page = 0;
        int size = 100;

        Page<User> userPage = null;
        while (userPage == null || page != userPage.getTotalPages()) {
            userPage = userService.findAllUsersByUserType(UserType.ARTIST, PageRequest.of(page, size));
            userPage.forEach(user -> sendEmailToArtist(user, message, subject));
            page++;
        }
        LOGGER.info("The message is complete");
    }

    public void sendEmailToArtist(User user, String message, String subject) {
        emailSender.send(user.getEmail().getValue(), subject, EmailTextBuilder.build(user.getName(), user.getSurName(), message));
    }
}
