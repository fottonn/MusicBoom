package ru.bugmakers.dto.response.mobile;

import ru.bugmakers.dto.Photos;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 15.12.2017.
 */
public class GetArtistResponse extends CommonResponseToMobile implements ResponseToMobile {
    public GetArtistResponse(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
    private Photos photos;
    private String artistName;
    private String creativity;
    private String instrument;
    private String genre;
    private String isOrdered;
    private String phoneNumber;
    private String vk;
    private String tlg;
    private String whatsApp;
    private String cityRaiting;
    private String countryRaiting;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCreativity() {
        return creativity;
    }

    public void setCreativity(String creativity) {
        this.creativity = creativity;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsOrdered() {
        return isOrdered;
    }

    public void setIsOrdered(String isOrdered) {
        this.isOrdered = isOrdered;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public String getTlg() {
        return tlg;
    }

    public void setTlg(String tlg) {
        this.tlg = tlg;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getCityRaiting() {
        return cityRaiting;
    }

    public void setCityRaiting(String cityRaiting) {
        this.cityRaiting = cityRaiting;
    }

    public String getCountryRaiting() {
        return countryRaiting;
    }

    public void setCountryRaiting(String countryRaiting) {
        this.countryRaiting = countryRaiting;
    }
}
