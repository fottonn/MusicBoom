package ru.bugmakers.controller.common.authentication;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.GoogleAuth;

/**
 * Created by Ivan
 */
@Component
public class GoogleAuthenticator extends AbstractAuthenticator {

    @Override
    protected User findUserBySocial(String id) {
        return getUserService().findUserByGoogleSocialId(id);
    }

    @Override
    protected boolean isValidSocialId(String token, String id) {
        return getSocialIdChecker().isValidGoogleId(token, id);
    }

    @Override
    protected User setSocialAuth(User user, String id) {
        GoogleAuth googleAuth = new GoogleAuth(id);
        user.setGoogleAuth(googleAuth);
        return user;
    }

}
