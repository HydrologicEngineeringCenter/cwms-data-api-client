/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.aaa.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class CwmsAAAAuthToken {

    @JsonProperty("username")
    private String username = null;

    @JsonProperty("last-login")
    private ZonedDateTime lastLogin = null;

    @JsonProperty("roles")
    private List<String> roles = new ArrayList<>();

    private String jSessionId;

    public String username() {
        return username;
    }

    public ZonedDateTime lastLogin() {
        return lastLogin;
    }

    public List<String> roles() {
        return new ArrayList<>(roles);
    }

    void setjSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    public String jSessionId() {
        return jSessionId;
    }
}
