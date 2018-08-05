package ru.bugmakers.exceptions;

public class MbUnregException extends MbException{

    private MbUnregException() {
    }

    private MbUnregException(MbError mbError) {
        super(mbError);
    }

    public static MbUnregException create(MbError mbError) {
        if (mbError == null) return new MbUnregException();
        return new MbUnregException(mbError);
    }

}
