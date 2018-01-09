package ru.bugmakers.dto.social;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ivan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VkUserInfoRs implements Serializable {

    private List<VkUserInfo> response;

    public List<VkUserInfo> getResponse() {
        return response;
    }

    public void setResponse(List<VkUserInfo> response) {
        this.response = response;
    }

    @JsonIgnore
    public VkUserInfo getVkUserInfo() {
        if (CollectionUtils.isEmpty(response)) return null;
        return response.get(0);
    }
}
