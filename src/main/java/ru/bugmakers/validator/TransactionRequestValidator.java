package ru.bugmakers.validator;

import io.jsonwebtoken.lang.Collections;
import org.apache.commons.codec.digest.DigestUtils;
import org.cfg4j.provider.ConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

import java.util.Map;

/**
 * Created by Ivan
 */
@Component
public class TransactionRequestValidator implements MbValidator<Map<String, String>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRequestValidator.class);

    public static final String SEPARATOR = "&";
    public static final String OPERATION_ID = "operation_id";
    public static final String AMOUNT = "amount";
    public static final String WITHDRAW_AMOUNT = "withdraw_amount";
    public static final String LABEL = "label";
    private static final String NOTIFICATION_TYPE = "notification_type";
    private static final String CURRENCY = "currency";
    private static final String DATETIME = "datetime";
    private static final String SENDER = "sender";
    private static final String CODEPRO = "codepro";

    private static final String SHA_1_HASH = "sha1_hash";

    private ConfigurationProvider appConfigProvider;

    @Autowired
    @Qualifier("appConfigProvider")
    public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
        this.appConfigProvider = appConfigProvider;
    }

    @Override
    public void validate(Map<String, String> target) throws MbException {

        if (Collections.isEmpty(target)) {
            LOGGER.error("Transaction values is empty");
            throw MbException.create(MbError.TRE05);
        }


        if (!target.containsKey(SHA_1_HASH)) {
            LOGGER.error("Transaction value {} is not present", SHA_1_HASH);
            throw MbException.create(MbError.TRE05);
        }

        if (!target.containsKey(OPERATION_ID)) {
            LOGGER.error("Transaction value {} is not present", OPERATION_ID);
            throw MbException.create(MbError.TRE05);
        }

        if (!target.containsKey(AMOUNT)) {
            LOGGER.error("Transaction value {} is not present", AMOUNT);
            throw MbException.create(MbError.TRE05);
        }

        if (!target.containsKey(LABEL)) {
            LOGGER.error("Transaction value {} is not present", LABEL);
            throw MbException.create(MbError.TRE05);
        }
        if (!target.containsKey(NOTIFICATION_TYPE)) {
            LOGGER.error("Transaction value {} is not present", NOTIFICATION_TYPE);
            throw MbException.create(MbError.TRE05);
        }
        if (!target.containsKey(CURRENCY)) {
            LOGGER.error("Transaction value {} is not present", CURRENCY);
            throw MbException.create(MbError.TRE05);
        }
        if (!target.containsKey(DATETIME)) {
            LOGGER.error("Transaction value {} is not present", DATETIME);
            throw MbException.create(MbError.TRE05);
        }
        if (!target.containsKey(SENDER)) {
            LOGGER.error("Transaction value {} is not present", SENDER);
            throw MbException.create(MbError.TRE05);
        }
        if (!target.containsKey(CODEPRO)) {
            LOGGER.error("Transaction value {} is not present", CODEPRO);
            throw MbException.create(MbError.TRE05);
        }

        StringBuilder sb = new StringBuilder()
                .append(target.get(NOTIFICATION_TYPE)).append(SEPARATOR)
                .append(target.get(OPERATION_ID)).append(SEPARATOR)
                .append(target.get(AMOUNT)).append(SEPARATOR)
                .append(target.get(CURRENCY)).append(SEPARATOR)
                .append(target.get(DATETIME)).append(SEPARATOR)
                .append(target.get(SENDER)).append(SEPARATOR)
                .append(target.get(CODEPRO)).append(SEPARATOR)
                .append(appConfigProvider.getProperty("transaction.notification.secret", String.class)).append(SEPARATOR)
                .append(target.get(LABEL));


        String sha1_hash = target.get(SHA_1_HASH);

        if (sha1_hash == null) {
            LOGGER.error("Transaction value {} is null", SHA_1_HASH);
            throw MbException.create(MbError.TRE05);
        }

        String calc_hash = DigestUtils.sha1Hex(sb.toString());

        if (!sha1_hash.equals(calc_hash)) {
            LOGGER.error("Transaction hash is not valid");
            throw MbException.create(MbError.TRE05);
        }

    }
}
