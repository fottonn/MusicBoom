package ru.bugmakers.controller.common.authentication;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.VkAuth;

/**
 * Created by Ivan
 */
@Component
public class VkAuthenticator extends AbstractAuthenticator {

    @Override
    protected User findUserBySocial(String id) {
        return getUserService().findUserByVkSocialId(id);
    }

    @Override
    protected boolean isValidSocialId(String token, String id) {
        return getSocialIdChecker().isValidVkId(token, id);
    }

    @Override
    protected User setSocialAuth(User user, String id) {
        VkAuth vkAuth = new VkAuth(id);
        user.setVkAuth(vkAuth);
        return user;
    }

}
