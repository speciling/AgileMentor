package agilementor.member.util;


import agilementor.member.dto.ParsedIdToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JwtParser {

    public Map<String, Claim> parseClaims(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims();
    }

    public ParsedIdToken parseIdToken(String token) {
        Map<String, Claim> claims = parseClaims(token);
        String email = claims.get("email").asString();
        String name = claims.get("name").asString();
        String picture = claims.get("picture").asString();

        return new ParsedIdToken(email, name, picture);
    }

}
