package pl.mkan.service.tools;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class UserIdFactory {

    public static UserId generateId() {
        OAuth2AuthenticationToken authentication =
                (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return switch (authentication.getAuthorizedClientRegistrationId()) {
            case "github" -> new GHUserId(authentication);
            case "google" -> new GoogleUserId(authentication);
            default -> throw new RuntimeException("Unsupported oauth2 authentication token");
        };
    }
}
