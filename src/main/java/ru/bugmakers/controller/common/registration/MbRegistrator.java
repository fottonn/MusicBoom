package ru.bugmakers.controller.common.registration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
@Qualifier("mbRegistrator")
public class MbRegistrator extends AbstractRegistrator {


    @Override
    protected boolean isValidSocialId(String token, String socialId) {
        return true;
    }

    @Override
    protected void checkExistsSocialId(String id) throws MbException {
        //stub
    }

    @Override
    protected User setSocialAuth(User user, String id) {
        return user;
    }
}
