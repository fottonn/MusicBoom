package ru.bugmakers.dto.request.mobile;

/**
 * Created by Ayrat on 21.11.2017.
 */
public class RegistrationListenerRequest {
    private String email;
    private String password;
    private String country;
    private String city;
    private String isPersonalData;
    private String isContract;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsPersonalData() {
        return isPersonalData;
    }

    public void setIsPersonalData(String isPersonalData) {
        this.isPersonalData = isPersonalData;
    }

    public String getIsContract() {
        return isContract;
    }

    public void setIsContract(String isContract) {
        this.isContract = isContract;
    }
}
