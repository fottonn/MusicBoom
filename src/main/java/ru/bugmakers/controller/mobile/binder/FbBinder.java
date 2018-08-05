package ru.bugmakers.controller.mobile.binder;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.FbAuth;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

@Component
public class FbBinder extends AbstractSocialBinder {

    @Override
    protected boolean isValidSocialId(String token, String socialId) {
        return getSocialIdChecker().isValidFbId(token, socialId);
    }

    @Override
    protected void checkExistsSocialId(String id) throws MbException {
        if (getUserService().isExistsByFbSocialId(id)) {
            throw MbException.create(MbError.RGE11);
        }
    }

    @Override
    protected User setSocialAuth(User user, String socialId) {
        FbAuth fbAuth = new FbAuth(socialId);
        user.setFbAuth(fbAuth);
        return user;
    }
}
