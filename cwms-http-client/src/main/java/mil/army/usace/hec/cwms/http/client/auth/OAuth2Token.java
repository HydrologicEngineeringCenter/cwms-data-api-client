package mil.army.usace.hec.cwms.http.client.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-16T09:13:30.631614-08:00[America/Los_Angeles]")
public class OAuth2Token {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private long expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("scope")
    private String scope;

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private static String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OAuth2Token {\n");

        sb.append("    access_token: ").append(toIndentedString(accessToken)).append("\n");
        sb.append("    token_type: ").append(toIndentedString(tokenType)).append("\n");
        sb.append("    expires_in: ").append(toIndentedString(expiresIn)).append("\n");
        sb.append("    refresh_token: ").append(toIndentedString(refreshToken)).append("\n");
        sb.append("    scope: ").append(toIndentedString(scope)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OAuth2Token that = (OAuth2Token) o;
        return getExpiresIn() == that.getExpiresIn() && Objects.equals(getAccessToken(), that.getAccessToken()) &&
            Objects.equals(getTokenType(), that.getTokenType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessToken(), getTokenType(), getExpiresIn());
    }
}
