package ru.bugmakers.controller.mobile.binder;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.VkAuth;

@Component
public class VkBinder extends AbstractSocialBinder {

    @Override
    protected boolean isValidSocialId(String token, String socialId) {
        boolean isValid = true;
        if (!getSocialIdChecker().isValidVkId(token, socialId)) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    protected User setSocialAuth(User user, String socialId) {
        VkAuth vkAuth = new VkAuth(socialId);
        user.setVkAuth(vkAuth);
        return user;
    }
}
