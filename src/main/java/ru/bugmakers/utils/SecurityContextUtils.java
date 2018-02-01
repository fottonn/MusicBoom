package ru.bugmakers.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ivan
 */
public class SecurityContextUtils {

    public static void setAuthentication(User user) throws MbException {
        //Проверяем необходимые поля для аутентификации
        Assert.notNull(user, "");
        Assert.notNull(user.getLogin(), "");
        Assert.notNull(user.getPhone(), "");
        if (!user.isEnabled()) throw MbException.create(MbError.AUE17);
        if (!user.isRegistered()) throw MbException.create(MbError.AUE18);
        //Устанавливаем SecurityContext
        UserPrincipal userPrincipal = new UserPrincipal(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
