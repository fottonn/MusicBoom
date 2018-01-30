package ru.bugmakers.controller.common.authentication;

import ru.bugmakers.enums.SocialProvider;

/**
 * Created by Ivan
 */
public class AuthenticatorFactory {

    public static Authenticator getAuthenticator(SocialProvider provider) {

        Authenticator authenticator = null;

        switch (provider) {
            case VK:
                authenticator = new VkAuthenticator();
                break;
            case FB:
                authenticator = new FbAuthenticator();
                break;
            case GOOGLE:
                authenticator = new GoogleAuthenticator();
                break;
        }

        return authenticator;

    }

}
