package ru.bugmakers.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.entity.User;

/**
 * Created by Ivan
 */
public class SecurityContextUtils {

    public static void setAuthentication(User user) {
        //Устанавливаем SecurityContext
        UserPrincipal userPrincipal = new UserPrincipal(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
