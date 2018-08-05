package ru.bugmakers.mappers.converters;

/**
 * Created by Ivan
 */
public interface MbConverter<T, S> {

    S convert(T source);

}
