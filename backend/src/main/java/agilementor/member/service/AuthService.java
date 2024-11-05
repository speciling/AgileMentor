package agilementor.member.service;

import agilementor.member.properties.GoogleClientProperties;
import agilementor.member.properties.GoogleProviderProperties;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final GoogleClientProperties clientProperties;
    private final GoogleProviderProperties providerProperties;

    public AuthService(GoogleClientProperties clientProperties,
        GoogleProviderProperties providerProperties) {
        this.clientProperties = clientProperties;
        this.providerProperties = providerProperties;
    }

    public String getAuthUrl() {
        return providerProperties.authorizationUri()
            + "?"
            + "response_type=code"
            + "&client_id=" + clientProperties.clientId()
            + "&scope=" + String.join(" ", clientProperties.scope())
            + "&redirect_uri=" + clientProperties.redirectUri();
    }
}
