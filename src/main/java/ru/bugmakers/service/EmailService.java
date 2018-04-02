package ru.bugmakers.service;

import com.google.common.base.Strings;
import okhttp3.HttpUrl;
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
    private static final String WEBAPI = "webapi";
    private static final String EMAIL = "email";
    private static final String CONFIRMATION = "confirmation";
    private static final String ACTIVATE = "activate";
    private static final String HTTPS = "https";
    private static final String CODE = "code";
    private static final String ID = "id";
    private static final String RESTOREPASSWORD = "restorepassword";

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
     * @throws MbException
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

        String confirmLink =
                new HttpUrl.Builder()
                        .scheme(HTTPS)
                        .host(HOST)
                        .addPathSegment(ACTIVATE)
                        .addPathSegment(generatedValue)
                        .build().toString();

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
            throw MbException.create(MbError.SEE05);
        } else {
            user.getEmail().setConfirmationCode(generatedValue);
            userService.updateUser(user);
        }
        String pswdRestoreLink =
                new HttpUrl.Builder()
                        .scheme(HTTPS)
                        .host(HOST)
                        .addPathSegment(RESTOREPASSWORD)
                        .addQueryParameter(EMAIL, email)
                        .addQueryParameter(ID, user.getId().toString())
                        .addQueryParameter(CODE, generatedValue)
                        .build().toString();

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
            emailRepo.save(email);
        } else {
            throw MbException.create(MbError.CME07);
        }

    }
}
