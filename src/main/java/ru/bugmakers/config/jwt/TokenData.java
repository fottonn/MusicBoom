package ru.bugmakers.config.jwt;

/**
 * Created by Ivan
 *
 * Константы для токена
 */
public interface TokenData {

    String TOKEN_KEY = "HxIL5n80sfjMIFsCovfy";
    String TOKEN_CREATE_DATE = "TOKEN_CREATE_DATE";
    String USERNAME = "USERNAME";
    String TOKEN_NAME = "sessionId";
    long TOKEN_LIFE_TIME = 172800; //2 дня в секундах
}
