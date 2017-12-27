package ru.bugmakers.errors;

/**
 * Created by Ayrat on 11.12.2017.
 */
public class Errors {
    private Boolean isError;
    private String description;

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
