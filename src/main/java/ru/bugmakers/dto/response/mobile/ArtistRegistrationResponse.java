package ru.bugmakers.dto.response.mobile;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bugmakers.dto.Photos;
import ru.bugmakers.dto.StatOfPerformance;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 15.12.2017.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ArtistRegistrationResponse extends MbResponseToMobile {

    private UserDTO userDTO;
    private String allEarnedMoney;
    private String allDerivedMoney;
    private String currentBalance;
    private String allDonatedArtists;
    private Photos photos;
    private StatOfPerformance statOfPerformance;

    public ArtistRegistrationResponse(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistRegistrationResponse(RsStatus status) {
        super(status);
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
