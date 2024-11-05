package agilementor.member.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.provider.google")
public record GoogleProviderProperties(
    String authorizationUri,
    String tokenUri
) {

}
