package ru.bugmakers.dto.request.web;

import ru.bugmakers.dto.CommonUser;

/**
 * Created by Ayrat on 11.12.2017.
 */
public class ArtistEditWebRequestWeb {
    private CommonUser atrist;

    public CommonUser getAtrist() {
        return atrist;
    }

    public void setAtrist(CommonUser atrist) {
        this.atrist = atrist;
    }
}
