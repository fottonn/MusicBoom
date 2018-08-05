package ru.bugmakers.dto.social;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Ivan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VkCheckTokenRs implements Serializable {

    private String success;

    @JsonProperty(value = "user_id")
    private String userId;

    private String date;

    private String expire;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }
}
