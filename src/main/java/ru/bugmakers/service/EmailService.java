package ru.bugmakers.service;

import org.apache.commons.mail.EmailException;
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

import java.util.ArrayList;
import java.util.List;

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
        try {
            emailSender.send(email, CONFIRMATION_SUBJECT, messageText);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw MbException.create(MbError.SEE01);
        }
    }

    public void sendEmailToAllArtists(String message, String subject) {

        final List<User> losers = new ArrayList<>();

        int page = 0;
        int size = 100;

        Page<User> userPage = null;
        while (userPage == null || page != userPage.getTotalPages()) {
            userPage = userService.findAllUsersByUserType(UserType.ARTIST, PageRequest.of(page, size));
            userPage.forEach(user -> {
                try {
                    sendEmailToArtist(user, message, subject);
                } catch (Exception e) {
                    losers.add(user);
                }
            });
            page++;
        }
        LOGGER.info("The message is complete");
        if (losers.isEmpty()) {
            LOGGER.info("The message sent successfully to all artists");
        } else {
            StringBuilder sb = new StringBuilder(String.format("The message not sent to %d loser(s):\n", losers.size()));
            losers.forEach(user -> sb.append(String.format("%6s %-30s\n", user.getId(), user.getEmail().getValue())));
            LOGGER.error(sb.toString());
        }

    }

    public void sendEmailToArtist(User user, String message, String subject) throws EmailException {
        emailSender.send(user.getEmail().getValue(), subject, EmailTextBuilder.build(user.getName(), user.getSurName(), message));
    }
}
