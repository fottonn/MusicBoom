package ru.bugmakers.controller.common.registration;

import org.springframework.stereotype.Component;
import ru.bugmakers.entity.User;

/**
 * Created by Ivan
 */
@Component
public class MbRegistrator extends AbstractRegistrator {

    @Override
    public User checkUserBySocial(Long id) {
        return null;
    }
}
