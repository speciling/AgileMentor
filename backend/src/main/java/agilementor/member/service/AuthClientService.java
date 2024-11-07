package agilementor.member.service;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

import agilementor.member.dto.GoogleTokenResponse;
import agilementor.member.properties.GoogleClientProperties;
import agilementor.member.properties.GoogleProviderProperties;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Service
public class AuthClientService {

    private final GoogleClientProperties clientProperties;
    private final GoogleProviderProperties providerProperties;
    private final RestClient restClient;

    public AuthClientService(GoogleClientProperties clientProperties,
        GoogleProviderProperties providerProperties) {
        this.clientProperties = clientProperties;
        this.providerProperties = providerProperties;
        restClient = RestClient.builder().build();
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

        return restClient.post()
            .uri(providerProperties.tokenUri())
            .contentType(APPLICATION_FORM_URLENCODED)
            .body(body)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                throw new IllegalArgumentException("구글 id 토큰 발급 실패");
            })
            .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                throw new RuntimeException("구글 인증 서버 에러");
            })
            .toEntity(GoogleTokenResponse.class)
            .getBody()
            .idToken();
    }

}
