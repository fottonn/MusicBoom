package ru.bugmakers.controller.mobile.binder;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.UserService;
import ru.bugmakers.utils.SocialIdChecker;

public abstract class AbstractSocialBinder implements SocialBinder {


    private UserService userService;
    private SocialIdChecker socialIdChecker;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSocialIdChecker(SocialIdChecker socialIdChecker) {
        this.socialIdChecker = socialIdChecker;
    }

    public SocialIdChecker getSocialIdChecker() {
        return socialIdChecker;
    }

    @Override
    public void bind(User user, String token, String socialId) throws MbException {

        if (!isValidSocialId(token, socialId)) {
            throw MbException.create(MbError.AUE16);
        }
        userService.saveUser(setSocialAuth(user, socialId));
    }

    protected abstract User setSocialAuth(User user, String socialId);

    protected abstract boolean isValidSocialId(String token, String socialId);
}
