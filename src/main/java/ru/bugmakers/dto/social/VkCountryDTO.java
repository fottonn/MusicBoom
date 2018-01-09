package ru.bugmakers.dto.social;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ivan
 */
public class VkCountryDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
