package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.CommonUser;
import ru.bugmakers.dto.Photos;
import ru.bugmakers.dto.StatOfPerformance;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class ArtistRegistrationResponse extends CommonResponseToMobile {
    public ArtistRegistrationResponse(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
    private CommonUser commonUser;
    //TODO возможно эти поля тоже добавить в CommonUser
    private String allEarnedMoney;
    private String allDerivedMoney;
    private String currentBalance;
    private String allDonatedArtists;
    private Photos photos;
    private StatOfPerformance statOfPerformance;

    public CommonUser getCommonUser() {
        return commonUser;
    }

    public void setCommonUser(CommonUser commonUser) {
        this.commonUser = commonUser;
    }

    public String getAllEarnedMoney() {
        return allEarnedMoney;
    }

    public void setAllEarnedMoney(String allEarnedMoney) {
        this.allEarnedMoney = allEarnedMoney;
    }

    public String getAllDerivedMoney() {
        return allDerivedMoney;
    }

    public void setAllDerivedMoney(String allDerivedMoney) {
        this.allDerivedMoney = allDerivedMoney;
    }


    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getAllDonatedArtists() {
        return allDonatedArtists;
    }

    public void setAllDonatedArtists(String allDonatedArtists) {
        this.allDonatedArtists = allDonatedArtists;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public StatOfPerformance getStatOfPerformance() {
        return statOfPerformance;
    }

    public void setStatOfPerformance(StatOfPerformance statOfPerformance) {
        this.statOfPerformance = statOfPerformance;
    }
}
