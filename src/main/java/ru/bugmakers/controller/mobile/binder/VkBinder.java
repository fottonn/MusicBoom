package ru.bugmakers.controller.mobile.binder;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.VkAuth;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

@Component
public class VkBinder extends AbstractSocialBinder {

    @Override
    protected boolean isValidSocialId(String token, String socialId) {
        return getSocialIdChecker().isValidVkId(token, socialId);
    }

    @Override
    protected void checkExistsSocialId(String id) throws MbException {
        if (getUserService().isExistsByVkSocialId(id)) {
            throw MbException.create(MbError.RGE10);
        }
    }

    @Override
    protected User setSocialAuth(User user, String socialId) {
        VkAuth vkAuth = new VkAuth(socialId);
        user.setVkAuth(vkAuth);
        return user;
    }
}
