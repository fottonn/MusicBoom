package ru.bugmakers.dto.response.web;

import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistEditingResponseWeb extends CommonResponseToWeb {
    public ArtistEditingResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
}
