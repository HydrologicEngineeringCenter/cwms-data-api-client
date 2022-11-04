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
public final class CwmsAAAAuthToken {

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

    public void setJSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    public String jSessionId() {
        return jSessionId;
    }

    public void setJSessionIdSso(String jSessionIdSso) {
        this.jSessionIdSso = jSessionIdSso;
    }

    public String jSessionIdSso() {
        return jSessionIdSso;
    }
}
