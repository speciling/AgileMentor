package agilementor.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import agilementor.common.exception.ExternalServerErrorException;
import agilementor.common.exception.SocialLoginFailException;
import agilementor.member.properties.GoogleClientProperties;
import agilementor.member.properties.GoogleProviderProperties;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@ExtendWith(MockitoExtension.class)
class AuthClientServiceTest {

    @Mock
    private GoogleClientProperties clientProperties;

    @Mock
    private GoogleProviderProperties providerProperties;

    private AuthClientService authClientService;

    private RestClient.Builder restClientBuilder = RestClient.builder();

    private MockRestServiceServer server;

    private final String CLIENT_ID = "CLIENT_ID";
    private final String CLIENT_SECRET = "CLIENT_SECRET";
    private final String REDIRECT_URI = "redirect-uri.com";
    private final Set<String> SCOPE = Set.of("scope1", "scope2");
    private final String AUTHORIZATION_URI = "https://authorization-uri.com";
    private final String TOKEN_URI = "https://token-uri.com";
    private final String CODE = "authorizationCode";

    @BeforeEach
    void setup() {
        server = MockRestServiceServer.bindTo(restClientBuilder).build();
        authClientService = new AuthClientService(clientProperties, providerProperties,
            restClientBuilder);
    }

    @Test
    @DisplayName("구글 인증 url을 만들 수 있다.")
    void getAuthUrl() throws URISyntaxException {
        // given
        given(clientProperties.clientId()).willReturn(CLIENT_ID);
        given(clientProperties.scope()).willReturn(SCOPE);
        given(clientProperties.redirectUri()).willReturn(REDIRECT_URI);
        given(providerProperties.authorizationUri()).willReturn(AUTHORIZATION_URI);

        // when
        UriComponents uriComponents = UriComponentsBuilder
            .fromUriString(authClientService.getAuthUrl())
            .encode().build();
        MultiValueMap<String, String> queryParams = uriComponents.getQueryParams();

        // then
        assertThat(uriComponents.toUriString().split("\\?")[0]).isEqualTo(AUTHORIZATION_URI);
        assertThat(queryParams.get("response_type").getFirst()).isEqualTo("code");
        assertThat(queryParams.get("client_id").getFirst()).isEqualTo(CLIENT_ID);
        assertThat(Set.of(queryParams.get("scope").getFirst().split("%20"))).isEqualTo(SCOPE);
        assertThat(queryParams.get("redirect_uri").getFirst()).isEqualTo(REDIRECT_URI);
    }

    @Test
    @DisplayName("구글 id토큰을 발급받을 수 있다.")
    void requestIdToken() {
        // given
        given(clientProperties.clientId()).willReturn(CLIENT_ID);
        given(clientProperties.clientSecret()).willReturn(CLIENT_SECRET);
        given(clientProperties.redirectUri()).willReturn(REDIRECT_URI);
        given(providerProperties.tokenUri()).willReturn(TOKEN_URI);

        Map<String, String> expectedBody = Map.of("client_id", CLIENT_ID, "client_secret",
            CLIENT_SECRET, "redirect_uri", REDIRECT_URI, "code", CODE);
        String idToken = "id.token";
        String expectedResult = "{\"id_token\": \"" + idToken + "\"}";

        server.expect(requestTo(TOKEN_URI))
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().formDataContains(expectedBody))
            .andRespond(withSuccess(expectedResult, MediaType.APPLICATION_JSON));

        // when
        String actualIdToken = authClientService.requestIdToken(CODE);

        // then
        assertThat(actualIdToken).isEqualTo(idToken);
    }

    @Test
    @DisplayName("id토큰 발급요청 결과 4xx 에러일 경우 SocialLoginFailException을 발생시킨다.")
    void requestIdTokenResult4xxError() {
        // given
        given(clientProperties.clientId()).willReturn(CLIENT_ID);
        given(clientProperties.clientSecret()).willReturn(CLIENT_SECRET);
        given(clientProperties.redirectUri()).willReturn(REDIRECT_URI);
        given(providerProperties.tokenUri()).willReturn(TOKEN_URI);

        server.expect(requestTo(TOKEN_URI))
            .andExpect(method(HttpMethod.POST))
            .andRespond(withBadRequest());

        // when
        // then
        assertThatThrownBy(() -> authClientService.requestIdToken(CODE))
            .isInstanceOf(SocialLoginFailException.class);
    }

    @Test
    @DisplayName("id토큰 발급요청 결과 5xx 에러일 경우 ExternalServerErrorException을 발생시킨다.")
    void requestIdTokenResult5xxError() {
        // given
        given(clientProperties.clientId()).willReturn(CLIENT_ID);
        given(clientProperties.clientSecret()).willReturn(CLIENT_SECRET);
        given(clientProperties.redirectUri()).willReturn(REDIRECT_URI);
        given(providerProperties.tokenUri()).willReturn(TOKEN_URI);

        server.expect(requestTo(TOKEN_URI))
            .andExpect(method(HttpMethod.POST))
            .andRespond(withServerError());

        // when
        // then
        assertThatThrownBy(() -> authClientService.requestIdToken(CODE))
            .isInstanceOf(ExternalServerErrorException.class);
    }


}