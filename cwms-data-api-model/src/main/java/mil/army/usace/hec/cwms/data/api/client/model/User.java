/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * User
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-12-19T13:08:07.460137300-08:00[America/Los_Angeles]")
public class User {

    @JsonProperty("user-name")
    private String userName = null;

    @JsonProperty("principal")
    private String principal = null;

    @JsonProperty("cac-auth")
    private Boolean cacAuth = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("roles")
    @Valid
    private Map<String, List<String>> roles = new HashMap<>();

    public User userName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User principal(String principal) {
        this.principal = principal;
        return this;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public User cacAuth(Boolean cacAuth) {
        this.cacAuth = cacAuth;
        return this;
    }

    public Boolean isCacAuth() {
        return cacAuth;
    }

    public void setCacAuth(Boolean cacAuth) {
        this.cacAuth = cacAuth;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User roles(Map<String, List<String>> roles) {
        this.roles = roles;
        return this;
    }

    public User putRolesItem(String key, List<String> rolesItem) {
            if (this.roles == null) {
            this.roles = new HashMap<>();
            }
        this.roles.put(key, rolesItem);
        return this;
    }

    public Map<String, List<String>> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, List<String>> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        User user = (User) o;
        return this.userName == null || user.userName == null?Objects.equals(this.userName, user.userName):this.userName.equalsIgnoreCase(user.userName)
         && this.principal == null || user.principal == null?Objects.equals(this.principal, user.principal):this.principal.equalsIgnoreCase(user.principal)
         && Objects.equals(this.cacAuth, user.cacAuth)
         && this.email == null || user.email == null?Objects.equals(this.email, user.email):this.email.equalsIgnoreCase(user.email)
         && Objects.equals(this.roles, user.roles)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName==null?0:userName.toLowerCase(), principal==null?0:principal.toLowerCase(), cacAuth, email==null?0:email.toLowerCase(), roles);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");
        
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    principal: ").append(toIndentedString(principal)).append("\n");
        sb.append("    cacAuth: ").append(toIndentedString(cacAuth)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
