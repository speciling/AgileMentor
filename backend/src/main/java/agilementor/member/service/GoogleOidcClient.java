package agilementor.member.service;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

import agilementor.member.dto.GoogleTokenResponse;
import agilementor.member.properties.GoogleProviderProperties;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Service
public class GoogleOidcClient {

    private final GoogleProviderProperties providerProperties;
    private final RestClient restClient;

    public GoogleOidcClient(GoogleProviderProperties providerProperties) {
        this.providerProperties = providerProperties;
        restClient = RestClient.builder().build();
    }

    public String callTokenApi(MultiValueMap<String, String> body) {
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
