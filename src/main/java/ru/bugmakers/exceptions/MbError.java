package ru.bugmakers.exceptions;

/**
 * Created by Ivan
 */
public enum MbError {

    //Неизвестная ошибка
    UNE01("Неизвестная ошибка",""),

    //Ошибки аутентификации
    AUE01("Ошибка аутентификации", "Сервис Вконтакте вернул некорректный code"),
    AUE02("Ошибка аутентификации", "Сервис Вконтакте вернул некорректный token"),
    AUE03("Ошибка аутентификации", "Сервис Вконтакте вернул некорректный id пользователя");

    private String title;
    private String message;
    MbError(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return this.name();
    }
}
