package ru.bugmakers.dto.social;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Ivan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VkUserInfo implements Serializable{

    @JsonProperty("id")
    private String id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("deactivated")
    private String deactivated;

    @JsonProperty("hidden")
    private String hidden;

    @JsonProperty("bdate")
    private String bdate;

    @JsonProperty("city")
    private VkCityDTO vkCityDTO;

    @JsonProperty("country")
    private VkCountryDTO vkCountryDTO;

    @JsonProperty("sex")
    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(String deactivated) {
        this.deactivated = deactivated;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public VkCityDTO getVkCityDTO() {
        return vkCityDTO;
    }

    public void setVkCityDTO(VkCityDTO vkCityDTO) {
        this.vkCityDTO = vkCityDTO;
    }

    public VkCountryDTO getVkCountryDTO() {
        return vkCountryDTO;
    }

    public void setVkCountryDTO(VkCountryDTO vkCountryDTO) {
        this.vkCountryDTO = vkCountryDTO;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
