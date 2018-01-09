package ru.bugmakers.dto.common;

/**
 * Created by Ayrat on 11.12.2017.
 */
public class ErrorDTO {

    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
