package ru.bugmakers.controller.mobile.binder;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.GoogleAuth;

@Component
public class GoogleBinder extends AbstractSocialBinder {

    @Override
    protected boolean isValidSocialId(String token, String socialId) {
        boolean isValid = true;
        if (!getSocialIdChecker().isValidGoogleId(token, socialId)) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    protected User setSocialAuth(User user, String socialId) {
        GoogleAuth googleAuth = new GoogleAuth(socialId);
        user.setGoogleAuth(googleAuth);
        return user;
    }
}
