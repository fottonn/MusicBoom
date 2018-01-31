package ru.bugmakers.controller.common.authentication;

import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bugmakers.controller.web.authentication.SocialAuthenticationConstants;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.social.VkUserInfoRs;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.VkAuth;
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
public class VkAuthenticator implements Authenticator, SocialAuthenticationConstants {

    private RestTemplate restTemplate;
    private UserService userService;
    private User2UserDtoConverter user2UserDtoConverter;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }

    @Override
    public UserDTO authenticate(String token, String id) throws MbException {

        final URI vkGetUserInfoUrl =
                new HttpUrl.Builder()
                        .scheme(HTTPS)
                        .host(VK_API_HOST)
                        .addPathSegment("method")
                        .addPathSegment("users.get")
                        .addQueryParameter("user_ids", id)
                        .addQueryParameter("access_token", token)
                        .addQueryParameter("v", VK_API_VERSION)
                        .build().uri();
        final VkUserInfoRs vkUserInfoRs = restTemplate.getForObject(vkGetUserInfoUrl, VkUserInfoRs.class);

        User user;
        if (vkUserInfoRs != null && vkUserInfoRs.getVkUserInfo() != null && vkUserInfoRs.getVkUserInfo().getId() != null
                && vkUserInfoRs.getVkUserInfo().getId().equals(id)) {
            user = userService.findUserByVkSocialId(id);
            if (user == null) {
                user = new User();
                VkAuth vkAuth = new VkAuth(id);
                vkAuth.setUser(user);
                user.setVkAuth(vkAuth);
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
