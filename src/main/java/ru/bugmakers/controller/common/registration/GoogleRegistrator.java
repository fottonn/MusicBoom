package ru.bugmakers.controller.common.registration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.GoogleAuth;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
@Qualifier("googleRegistrator")
public class GoogleRegistrator extends AbstractRegistrator {

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
    protected User setSocialAuth(User user, String id) {
        GoogleAuth googleAuth = new GoogleAuth(id);
        user.setGoogleAuth(googleAuth);
        return user;
    }
}
