package ru.bugmakers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

/**
 * Created by Ayrat on 30.01.2018.
 */
@Component
public class SendEmail {
    //TODO захардкодить email
    private static final String FROM_EMAIL = "bm@musboom.ru";
    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public Boolean sendEmail(String email, String text) throws MbException {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipients(Message.RecipientType.TO, email);
                mimeMessage.setFrom(new InternetAddress(FROMEMAIL));
                mimeMessage.setText(text);
            }
        };
        try {
            this.javaMailSender.send(preparator);
        } catch (MailException e) {
            throw MbException.create(MbError.SEE01);
        }
        return Boolean.TRUE;
    }
}
