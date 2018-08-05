package ru.bugmakers.controller.common.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.bugmakers.enums.SocialProvider;

/**
 * Created by Ivan
 */
@Component
public class AuthenticatorCreator {

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public Authenticator getAuthenticator(SocialProvider provider) {

        Authenticator authenticator = null;

        switch (provider) {
            case VK:
                authenticator = context.getBean(VkAuthenticator.class);
                break;
            case FB:
                authenticator = context.getBean(FbAuthenticator.class);
                break;
            case GOOGLE:
                authenticator = context.getBean(GoogleAuthenticator.class);
                break;
        }

        return authenticator;

    }

}
