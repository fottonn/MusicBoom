package ru.bugmakers.service;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.Email;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.repository.EmailRepo;
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
    private static final String PSWD_RESTORE_SUBJECT = "Восстановление пароля";
    private static final String HOST = "www.musboom.ru";
    private static final String HTTPS = "https";
    private static final String CODE = "code";
    private static final String ID = "id";
    private static final String SHARP = "#";
    private static final String FINISH_RECOVER = "finish_recover";
    private static final String FINISH_REGISTRATION = "finish_registration";
    private static final String CONFIRM_LINK = "https://www.musboom.ru/#/finish_registration/";
    private static final String RESTORE_LINK = "https://www.musboom.ru/#/finish_recover";

    private EmailSender emailSender;
    private UserService userService;
    private EmailRepo emailRepo;

    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEmailRepo(EmailRepo emailRepo) {
        this.emailRepo = emailRepo;
    }

    /**
     * Метод валидации email
     *
     * @param user пользователь
     */

    public void sendConfirmationEmail(User user) throws MbException {
        if (user == null || user.getEmail() == null) return;
        String generatedValue = UuidGenerator.timeBasedUuidGenerate();
        user.getEmail().setConfirmationCode(generatedValue);
        try {
            user = userService.saveUser(user);
        } catch (Exception e) {
            throw MbException.create(MbError.SEE02);
        }
        String email = user.getEmail().getValue();
        String confirmLink = new StringBuilder(CONFIRM_LINK).append(generatedValue).toString();
        String messageText = EmailTextBuilder.confirmBuild(user.getName(), user.getSurName(), confirmLink);
        emailSender.send(email, CONFIRMATION_SUBJECT, messageText);
    }

    public void sendPasswordRestoreEmail(final String email) throws MbException {
        if (email == null) return;
        String generatedValue = UuidGenerator.timeBasedUuidGenerate();
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw MbException.create(MbError.SEE04);
        } else if (!user.getEmail().isEnabled()) {
            sendConfirmationEmail(user);
            throw MbException.create(MbError.SEE05);
        } else {
            user.setPasswordChangeCode(generatedValue);
            userService.updateUser(user);
        }

        String pswdRestoreLink =
                new StringBuilder(RESTORE_LINK).append("?")
                        .append(ID).append("=").append(user.getId().toString()).append("&")
                        .append(CODE).append("=").append(generatedValue)
                        .toString();
        String messageText = EmailTextBuilder.pswdRestoreBuild(user.getName(), user.getSurName(), pswdRestoreLink);
        emailSender.send(email, PSWD_RESTORE_SUBJECT, messageText);
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

    /**
     * Отправка письма пользователю, у которого email подтвержден
     *
     * @param user    пользователь, которому отправляется письмо
     * @param message сообщение
     * @param subject тема письма
     */
    public void sendEmailToArtist(User user, String message, String subject) {
        if (user != null && user.getEmail() != null && user.getEmail().isEnabled()) {
            emailSender.send(user.getEmail().getValue(), subject, EmailTextBuilder.build(user.getName(), user.getSurName(), message));
        }
    }

    public void checkConfirmationCode(String code) throws MbException {
        if (Strings.isNullOrEmpty(code)) throw MbException.create(MbError.CME07);
        Email email = emailRepo.findByConfirmationCode(code);
        if (email != null) {
            email.setEnabled(true);
            email.setConfirmationCode(null);
            emailRepo.saveAndFlush(email);
        } else {
            throw MbException.create(MbError.CME07);
        }

    }
}
