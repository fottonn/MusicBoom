package ru.bugmakers.controller.common.registration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.VkAuth;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
@Qualifier("vkRegistrator")
public class VkRegistrator extends AbstractRegistrator {

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
    protected User setSocialAuth(User user, String id) {
        VkAuth vkAuth = new VkAuth(id);
        user.setVkAuth(vkAuth);
        return user;
    }

}
