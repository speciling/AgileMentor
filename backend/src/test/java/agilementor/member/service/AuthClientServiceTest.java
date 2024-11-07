package agilementor.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import agilementor.member.properties.GoogleClientProperties;
import agilementor.member.properties.GoogleProviderProperties;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
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

    @InjectMocks
    private AuthClientService authClientService;

    private MockRestServiceServer server = MockRestServiceServer
        .bindTo(RestClient.builder()).build();

    private final String CLIENT_ID = "CLIENT_ID";
    private final String CLIENT_SECRET = "CLIENT_SECRET";
    private final String REDIRECT_URI = "redirect-uri.com";
    private final Set<String> SCOPE = Set.of("scope1", "scope2");
    private final String AUTHORIZATION_URI = "https://authorization-uri.com";
    private final String TOKEN_URI = "https://token-uri.com";
    private final String CODE = "authorizationCode";

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
}