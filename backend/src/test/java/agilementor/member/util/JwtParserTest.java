package agilementor.member.util;

import static org.assertj.core.api.Assertions.assertThat;

import agilementor.member.dto.ParsedIdToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtParserTest {

    private final JwtParser jwtParser = new JwtParser();

    private String makeTestToken(Map<String, String> claims) {
        Algorithm algorithm = Algorithm.none();
        return JWT.create().withPayload(claims).sign(algorithm);
    }

    @Test
    @DisplayName("jwt토큰을 파싱할 수 있다")
    void parseClaims() {
        // given
        Map<String, String> claims = new HashMap<>();
        claims.put("key1", "value1");
        claims.put("key2", "value2");
        claims.put("key3", "value3");
        String testToken = makeTestToken(claims);

        // when
        Map<String, Claim> parsedClaims = jwtParser.parseClaims(testToken);

        // then
        for(String key : claims.keySet()) {
            assertThat(parsedClaims.get(key).asString()).isEqualTo(claims.get(key));
        }
    }

    @Test
    @DisplayName("구글 id토큰을 파싱할 수 있다")
    void parseIdToken() {
        // given
        Map<String, String> claims = new HashMap<>();
        claims.put("email", "email@email.com");
        claims.put("name", "name");
        claims.put("picture", "picture.jpg");
        String testToken = makeTestToken(claims);

        // when
        ParsedIdToken parsedIdToken = jwtParser.parseIdToken(testToken);

        // then
        assertThat(parsedIdToken.email()).isEqualTo(claims.get("email"));
        assertThat(parsedIdToken.name()).isEqualTo(claims.get("name"));
        assertThat(parsedIdToken.picture()).isEqualTo(claims.get("picture"));
    }
}