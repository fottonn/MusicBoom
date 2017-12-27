package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.CommonUser;
import ru.bugmakers.errors.Errors;

import java.util.List;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistInfoFromAdminConsoleResponseWeb extends CommonResponseToWeb {

    public ArtistInfoFromAdminConsoleResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
    private List<CommonUser> commonUsers;

    public List<CommonUser> getCommonUsers() {
        return commonUsers;
    }

    public void setCommonUsers(List<CommonUser> commonUsers) {
        this.commonUsers = commonUsers;
    }
}
