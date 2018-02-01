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
public class VkRegistrator extends AbstractRegistrator {

    @Override
    public User checkUserBySocial(Long id) throws MbException {
        User user = getUserService().findUserById(id);
        if (user == null || user.getVkAuth() == null || StringUtils.isBlank(user.getVkAuth().getSocialId())) {
            throw MbException.create(MbError.RGE05);
        }
        return user;
    }

}
