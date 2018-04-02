package ru.bugmakers.validator;

import org.springframework.stereotype.Component;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
public class EmailValidator implements MbValidator<String> {
    @Override
    public void validate(String target) throws MbException {
        if (target == null || !target.matches(".+@\\w+\\.\\w+")) {
            throw MbException.create(MbError.CME06);
        }
    }
}
