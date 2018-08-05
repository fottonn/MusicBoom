package ru.bugmakers.controller.common.registration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.FbAuth;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
@Qualifier("fbRegistrator")
public class FbRegistrator extends AbstractRegistrator {

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
    protected User setSocialAuth(User user, String id) {
        FbAuth fbAuth = new FbAuth(id);
        user.setFbAuth(fbAuth);
        return user;
    }
}
