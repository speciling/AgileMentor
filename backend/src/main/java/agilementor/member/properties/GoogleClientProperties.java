package agilementor.member.properties;

import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.client.google")
public record GoogleClientProperties(
    String clientId,
    String clientSecret,
    String redirectUri,
    Set<String> scope
) {

}
