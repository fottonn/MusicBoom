package ru.bugmakers.controller.common.registration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
@Component
@Qualifier("fbRegistrator")
public class FbRegistrator extends AbstractRegistrator {

    @Override
    public User checkUserBySocial(Long id) throws MbException {
        User user = getUserService().findUserById(id);
        if (user == null || user.getFbAuth() == null || StringUtils.isBlank(user.getFbAuth().getSocialId())) {
            throw MbException.create(MbError.RGE06);
        }
        return user;
    }
}
