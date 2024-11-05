package agilementor.member.service;

import agilementor.member.properties.GoogleClientProperties;
import agilementor.member.properties.GoogleProviderProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class AuthService {

    private final GoogleClientProperties clientProperties;
    private final GoogleProviderProperties providerProperties;
    private final GoogleOidcClient oidcClient;

    public AuthService(GoogleClientProperties clientProperties,
        GoogleProviderProperties providerProperties, GoogleOidcClient oidcClient) {
        this.clientProperties = clientProperties;
        this.providerProperties = providerProperties;
        this.oidcClient = oidcClient;
    }

    public String getAuthUrl() {
        return providerProperties.authorizationUri()
            + "?"
            + "response_type=code"
            + "&client_id=" + clientProperties.clientId()
            + "&scope=" + String.join(" ", clientProperties.scope())
            + "&redirect_uri=" + clientProperties.redirectUri();
    }

    public String requestIdToken(String code) {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("client_id", clientProperties.clientId());
        body.add("client_secret", clientProperties.clientSecret());
        body.add("redirect_uri", clientProperties.redirectUri());
        body.add("grant_type", "authorization_code");

        return oidcClient.callTokenApi(body);
    }

}
