package ru.bugmakers.controller.mobile.binder;

import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbException;

public interface SocialBinder {

    void bind(User user, String token, String socialId) throws MbException;

}
