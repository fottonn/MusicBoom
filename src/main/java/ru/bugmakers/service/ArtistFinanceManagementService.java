package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bugmakers.dto.request.mobile.CardInfoRequestMobile;
import ru.bugmakers.entity.User;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 19.02.2018.
 */
@Component
public class ArtistFinanceManagementService  {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Boolean attachCard(String id, CardInfoRequestMobile cardInfoRequestMobile) throws MbException {
        User userById = userService.findUserById(Long.valueOf(id));
        if (userById == null) {
            throw MbException.create(MbError.CME03);
        }
        userById.setCardNumber(cardInfoRequestMobile.getCardNumber());
        User user = userService.saveUser(userById);
        if (user == null) {
            throw MbException.create(MbError.CME04);
        }
        return Boolean.TRUE;
    }

    public Boolean detachCard(String id) throws MbException {
        User userById = userService.findUserById(Long.valueOf(id));
        if (userById == null) {
            throw MbException.create(MbError.CME03);
        }
        userById.setCardNumber(null);
        User user = userService.saveUser(userById);
        if (user == null) {
            throw MbException.create(MbError.CME04);
        }
        return Boolean.TRUE;
    }

    public Boolean withdraw(String id, String summ) throws MbException {
        User userById = userService.findUserById(Long.valueOf(id));
        if (userById == null) {
            throw MbException.create(MbError.CME03);
        }

        return null;
    }
}
