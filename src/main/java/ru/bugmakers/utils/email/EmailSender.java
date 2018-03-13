package ru.bugmakers.utils.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.cfg4j.provider.ConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Created by Ivan
 */
@Component
public class EmailSender {

    private ConfigurationProvider emailConfigProvider;

    @Autowired
    public EmailSender(@Qualifier("emailConfigProvider") ConfigurationProvider emailConfigProvider) {
        this.emailConfigProvider = emailConfigProvider;
    }

    public void send(String email, String subject, String text) throws EmailException {
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setFrom(
                emailConfigProvider.getProperty("email.from.address", String.class),
                emailConfigProvider.getProperty("email.from.name", String.class));
        htmlEmail.setAuthentication(
                emailConfigProvider.getProperty("email.username", String.class),
                emailConfigProvider.getProperty("email.password", String.class));
        htmlEmail.setHostName(emailConfigProvider.getProperty("email.host", String.class));
        htmlEmail.setSSLOnConnect(true);
        htmlEmail.setSslSmtpPort(emailConfigProvider.getProperty("email.port", String.class));
        htmlEmail.setCharset(StandardCharsets.UTF_8.name());
        htmlEmail.addTo(email);
        htmlEmail.setSubject(subject);
        htmlEmail.setHtmlMsg(text);
        htmlEmail.send();
    }

}
