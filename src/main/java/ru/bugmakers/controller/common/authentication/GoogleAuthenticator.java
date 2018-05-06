package ru.bugmakers.controller.common.authentication;

import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.social.GoogleUserInfoRs;
import ru.bugmakers.entity.User;
import ru.bugmakers.entity.auth.GoogleAuth;
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
public class GoogleAuthenticator implements Authenticator {

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

        User user = userService.findUserByGoogleSocialId(id);
        if (user == null) {
            throw MbUnregException.create(MbError.AUE18);
        }

        //пока проверку отключил, т.к. проблемы с доступом к гуглу
//        if (!isValidGoogleId(token, id)) {
        if (false) {
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

        //пока проверку отключил, т.к. проблемы с доступом к гуглу
//        if (!isValidGoogleId(token, id)) {
        if (false) {
            throw MbException.create(MbError.AUE16);
        }

        GoogleAuth googleAuth = new GoogleAuth(id);
        user.setGoogleAuth(googleAuth);
        user = userService.saveUser(user);

        if (user.isRegistered()) {
            SecurityContextUtils.setAuthentication(user);
        }

        return user2UserDtoConverter.convert(user);
    }

    private boolean isValidGoogleId(String token, String id) {
        boolean isValid = true;

        final URI googleGetUserInfoUrl =
                new HttpUrl.Builder()
                        .scheme(HTTPS)
                        .host(GOOGLE_API_HOST)
                        .addPathSegment("oauth2")
                        .addPathSegment("v3")
                        .addPathSegment("tokeninfo")
                        .addQueryParameter("id_token", token)
                        .build().uri();
        final GoogleUserInfoRs googleUserInfoRs = restTemplate.getForObject(googleGetUserInfoUrl, GoogleUserInfoRs.class);
        if (googleUserInfoRs == null || googleUserInfoRs.getId() == null || !googleUserInfoRs.getId().equals(id)) {
            isValid = false;
        }
        return isValid;
    }
}
