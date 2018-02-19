package ru.bugmakers.utils;

import org.springframework.stereotype.Component;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Ayrat on 30.01.2018.
 */

@Component
public class SendEmail {
    private String host;
    private String port;
    private String password;
    private String username;
    private static final String FROM = "bm@musboom.ru";

    @PostConstruct
    public void postConstruct() throws IOException {
        Properties props = new Properties();
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("email.properties").getFile());
            InputStream inputStream = new FileInputStream(file);
            props.load(inputStream);
            host = props.getProperty("email.host", "smtp.yandex.ru");
            port = props.getProperty("email.port", "465");
            password = props.getProperty("email.password", "PtktyfzFrekf@18202080");
            username = props.getProperty("email.username", "info@musboom.ru");
    }
    public Boolean sendEmail(String email, String text) throws MbException {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@musboom.ru"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("СРОЧНО!!");
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            throw MbException.create(MbError.SEE01);
        }

        return Boolean.TRUE;
    }
}
