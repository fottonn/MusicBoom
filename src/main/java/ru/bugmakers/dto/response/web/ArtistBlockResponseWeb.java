package ru.bugmakers.dto.response.web;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistBlockResponseWeb extends CommonResponseToWeb{
    public ArtistBlockResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
}
