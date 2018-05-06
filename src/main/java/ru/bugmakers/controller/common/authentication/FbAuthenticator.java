package ru.bugmakers.controller.common.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.FbAuth;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.exceptions.MbUnregException;
import ru.bugmakers.mappers.converters.User2UserDtoConverter;
import ru.bugmakers.service.UserService;
import ru.bugmakers.utils.SecurityContextUtils;
import ru.bugmakers.utils.SocialIdChecker;

/**
 * Created by Ivan
 */
@Component
public class FbAuthenticator implements Authenticator {

    private User2UserDtoConverter user2UserDtoConverter;
    private UserService userService;
    private SocialIdChecker socialIdChecker;

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSocialIdChecker(SocialIdChecker socialIdChecker) {
        this.socialIdChecker = socialIdChecker;
    }

    @Override
    public UserDTO authenticate(String token, String id) throws MbException {

        User user = userService.findUserByFbSocialId(id);
        if (user == null) {
            throw MbUnregException.create(MbError.AUE18);
        }

        if (!socialIdChecker.isValidFbId(token, id)) {
            throw MbException.create(MbError.AUE16);
        }

        if (user.isRegistered()) {
            SecurityContextUtils.setAuthentication(user);
        }

        return user2UserDtoConverter.convert(user);
    }

    @Override
    public UserDTO authenticate(String token, String id, String phone) throws MbException {

        User user = userService.findUserByPhone(phone);
        if (user == null) {
            throw MbUnregException.create(MbError.AUE18);
        }

        if (!socialIdChecker.isValidFbId(token, id)) {
            throw MbException.create(MbError.AUE16);
        }

        FbAuth fbAuth = new FbAuth(id);
        user.setFbAuth(fbAuth);
        user = userService.saveUser(user);

        if (user.isRegistered()) {
            SecurityContextUtils.setAuthentication(user);
        }

        return user2UserDtoConverter.convert(user);
    }
}
