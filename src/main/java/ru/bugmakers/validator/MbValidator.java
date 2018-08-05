package ru.bugmakers.validator;

import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
public interface MbValidator<T> {

    void validate(T target) throws MbException;

}
