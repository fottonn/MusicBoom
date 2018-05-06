package ru.bugmakers.exceptions;

/**
 * Created by ivan
 */
public class MbException extends Exception {

    private String code;
    private String title;
    private String description;

    protected MbException() {
    }

    protected MbException(String code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

    protected MbException(String title, String description) {
        this(null, title, description);
    }

    protected MbException(MbError mbError) {
        this.code = mbError.getCode();
        this.title = mbError.getTitle();
        this.description = mbError.getMessage();
    }

    public static MbException create(String code, String title, String description) {
        return new MbException(code, title, description);
    }

    public static MbException create(String title, String description) {
        return new MbException(title, description);
    }

    public static MbException create(MbError mbError) {
        if (mbError == null) return new MbException();
        return new MbException(mbError);
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
