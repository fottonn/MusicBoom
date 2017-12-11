package ru.bugmakers.errors;

/**
 * Created by Ayrat on 11.12.2017.
 */
public class Errors {
    private Boolean isError;
    private String descpription;

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public String getDescpription() {
        return descpription;
    }

    public void setDescpription(String descpription) {
        this.descpription = descpription;
    }
}
