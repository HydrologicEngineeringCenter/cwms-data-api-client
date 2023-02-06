/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.aaa.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class CwmsAuthToken {

    @JsonProperty("username")
    private String username = null;

    @JsonProperty("last-login")
    private ZonedDateTime lastLogin = null;

    @JsonProperty("roles")
    private List<String> roles = new ArrayList<>();

    private String jSessionId;
    private String jSessionIdSso;

    public String username() {
        return username;
    }

    public ZonedDateTime lastLogin() {
        return lastLogin;
    }

    public List<String> roles() {
        return new ArrayList<>(roles);
    }

    void setJSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    /**
     * The JSESSIONID cookie is used for session tracking in the CWMS_AAA web app.
     * The timeout is set server-side and a new cookie will need to be retrieved
     * if a HTTP 401 error code is returned.
     * @return cookie values for JSESSIONID
     */
    public String jSessionId() {
        return jSessionId;
    }

    void setJSessionIdSso(String jSessionIdSso) {
        this.jSessionIdSso = jSessionIdSso;
    }

    /**
     * The JSESSIONIDSSO cookie is used for session tracking across the CWMS Tomcat instance.
     * This specific token is use for authentication across all CWMS apps hosted
     * on the tomcat server. The timeout is set server-side and a new cookie will need to
     * be retrieved if a HTTP 401 error code is returned.
     * @return cookie values for JSESSIONIDSSO
     */
    public String jSessionIdSso() {
        return jSessionIdSso;
    }
}
