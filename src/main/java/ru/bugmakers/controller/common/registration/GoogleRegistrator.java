package ru.bugmakers.controller.common.registration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
public class GoogleRegistrator extends AbstractRegistrator {

    @Override
    public User checkUserBySocial(Long id) throws MbException {
        User user = getUserService().findUserById(id);
        if (user == null || user.getGoogleAuth() == null || StringUtils.isBlank(user.getGoogleAuth().getSocialId())) {
            throw MbException.create(MbError.RGE07);
        }
        return user;
    }
}
