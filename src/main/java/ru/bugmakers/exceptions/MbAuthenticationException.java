package ru.bugmakers.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Ivan
 *
 * Обертка для {@link MbException}
 */
public class MbAuthenticationException extends AuthenticationException {

    private MbException mbException;

    public MbAuthenticationException(MbException mbException) {
        super(mbException.getMessage());
        this.mbException = mbException;
    }

    public MbException getMbException() {
        return mbException;
    }
}
