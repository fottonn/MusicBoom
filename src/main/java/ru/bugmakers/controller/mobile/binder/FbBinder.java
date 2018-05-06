package ru.bugmakers.controller.mobile.binder;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.FbAuth;

@Component
public class FbBinder extends AbstractSocialBinder {

    @Override
    protected boolean isValidSocialId(String token, String socialId) {
        boolean isValid = true;
        if (!getSocialIdChecker().isValidFbId(token, socialId)) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    protected User setSocialAuth(User user, String socialId) {
        FbAuth fbAuth = new FbAuth(socialId);
        user.setFbAuth(fbAuth);
        return user;
    }
}
