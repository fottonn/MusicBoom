package ru.bugmakers.exceptions;

/**
 * Created by ivan
 */
public class MbException extends Exception{

    private String code;

    private String title;

    private String description;

    public MbException(String code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

    public MbException(String title, String description) {
        this(null, title, description);
    }

    public MbException(MbError mbError) {
        this.code = mbError.getCode();
        this.title = mbError.getTitle();
        this.description = mbError.getMessage();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getMessage() {
        return String.format("%s(%s): %s", title, code, description);
    }
}
