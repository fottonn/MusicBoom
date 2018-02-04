package ru.bugmakers.controller.common.registration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;

/**
 * Created by Ivan
 */
@Component
@Qualifier("mbRegistrator")
public class MbRegistrator extends AbstractRegistrator {

    @Override
    public User checkUserBySocial(Long id) {
        return null;
    }
}
