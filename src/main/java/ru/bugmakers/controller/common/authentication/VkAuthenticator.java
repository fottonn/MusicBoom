package ru.bugmakers.controller.common.authentication;

import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.social.VkUserInfoRs;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.VkAuth;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.exceptions.MbUnregException;
import ru.bugmakers.mappers.converters.User2UserDtoConverter;
import ru.bugmakers.service.UserService;
import ru.bugmakers.utils.SecurityContextUtils;

import java.net.URI;

/**
 * Created by Ivan
 */
@Component
public class VkAuthenticator implements Authenticator {

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

        User user = userService.findUserByVkSocialId(id);
        if (user == null) {
            throw MbUnregException.create(MbError.AUE18);
        }

        if (!isValidVkId(token, id)) {
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

        if (!isValidVkId(token, id)) {
            throw MbException.create(MbError.AUE16);
        }

        VkAuth vkAuth = new VkAuth(id);
        user.setVkAuth(vkAuth);
        user = userService.saveUser(user);

        if (user.isRegistered()) {
            SecurityContextUtils.setAuthentication(user);
        }

        return user2UserDtoConverter.convert(user);
    }

    private boolean isValidVkId(String token, String id) {
        boolean isValid = true;
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

        if (vkUserInfoRs == null || vkUserInfoRs.getVkUserInfo() == null || vkUserInfoRs.getVkUserInfo().getId() == null
                || !vkUserInfoRs.getVkUserInfo().getId().equals(id)) {
            isValid = false;
        }
        return isValid;
    }
}
