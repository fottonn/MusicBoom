package ru.bugmakers.mappers;

/**
 * Created by Ivan
 */
public interface MbConverter<T, S> {

    S convert(T source);

}
