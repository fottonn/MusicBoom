package ru.bugmakers.controller.web.authentication;

import ru.bugmakers.controller.MbController;
import ru.bugmakers.utils.DateTimeFormatters;

/**
 * Created by Ivan
 */
abstract class SocialAuthenticationWeb extends MbController implements DateTimeFormatters, SocialAuthenticationConstants {

    String redirectTo(String url) {
        return "redirect:" + url;
    }

}
