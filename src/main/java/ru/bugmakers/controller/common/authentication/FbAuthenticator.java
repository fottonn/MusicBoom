package ru.bugmakers.controller.common.authentication;

import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.social.FbUserInfoRs;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.FbAuth;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.converters.User2UserDtoConverter;
import ru.bugmakers.service.UserService;
import ru.bugmakers.utils.SecurityContextUtils;

import java.net.URI;

/**
 * Created by Ivan
 */
@Component
public class FbAuthenticator implements Authenticator {

    private RestTemplate restTemplate;
    private User2UserDtoConverter user2UserDtoConverter;
    private UserService userService;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO authenticate(String token, String id) throws MbException {

        final URI fbGetUserInfoUrl =
                new HttpUrl.Builder()
                        .scheme(HTTPS)
                        .host(FB_GRAF_HOST)
                        .addPathSegment("me")
                        .addQueryParameter("access_token", token)
                        .addQueryParameter("fields", "id")
                        .build().uri();
        final FbUserInfoRs fbUserInfoRs = restTemplate.getForObject(fbGetUserInfoUrl, FbUserInfoRs.class);

        User user;
        if (fbUserInfoRs != null && fbUserInfoRs.getId() != null && fbUserInfoRs.getId().equals(id)) {
            user = userService.findUserByFbSocialId(id);
            if (user == null) {
                user = new User();
                user.setFbAuth(new FbAuth(id));
                user = userService.saveUser(user);
            }
        } else {
            throw MbException.create(MbError.AUE16);
        }

        if (user.isRegistered()) {
            SecurityContextUtils.setAuthentication(user);
        }
        return user2UserDtoConverter.convert(user);
    }
}
