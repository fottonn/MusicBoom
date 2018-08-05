package ru.bugmakers.controller.mobile.binder;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.GoogleAuth;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

@Component
public class GoogleBinder extends AbstractSocialBinder {

    @Override
    protected boolean isValidSocialId(String token, String socialId) {
        return getSocialIdChecker().isValidGoogleId(token, socialId);
    }

    @Override
    protected void checkExistsSocialId(String id) throws MbException {
        if (getUserService().isExistsByGoogleSocialId(id)) {
            throw MbException.create(MbError.RGE12);
        }
    }

    @Override
    protected User setSocialAuth(User user, String socialId) {
        GoogleAuth googleAuth = new GoogleAuth(socialId);
        user.setGoogleAuth(googleAuth);
        return user;
    }
}
