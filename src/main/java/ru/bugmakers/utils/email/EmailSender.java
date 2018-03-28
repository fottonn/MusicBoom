package ru.bugmakers.utils.email;

import com.google.common.base.Strings;
import org.apache.commons.mail.HtmlEmail;
import org.cfg4j.provider.ConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Created by Ivan
 */
@Component
public class EmailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);

    private ConfigurationProvider emailConfigProvider;

    @Autowired
    public EmailSender(@Qualifier("emailConfigProvider") ConfigurationProvider emailConfigProvider) {
        this.emailConfigProvider = emailConfigProvider;
    }

    @Async
    public void send(String email, String subject, String text) {
        if (Strings.isNullOrEmpty(email)) return;
        try {
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
        } catch (Exception e) {
            LOGGER.error("Email not sent to " + email, e);
        }
    }

}
