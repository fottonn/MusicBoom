package ru.bugmakers.controller.common.authentication;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.FbAuth;

/**
 * Created by Ivan
 */
@Component
public class FbAuthenticator extends AbstractAuthenticator {

    @Override
    protected User findUserBySocial(String id) {
        return getUserService().findUserByFbSocialId(id);
    }

    @Override
    protected boolean isValidSocialId(String token, String id) {
        return getSocialIdChecker().isValidFbId(token, id);
    }

    @Override
    protected User setSocialAuth(User user, String id) {
        FbAuth fbAuth = new FbAuth(id);
        user.setFbAuth(fbAuth);
        return user;
    }
}
