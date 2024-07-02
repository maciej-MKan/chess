package pl.mkan.service.tools;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class GoogleUserId implements UserId {

    private final OAuth2AuthenticationToken authorization;

    @Override
    public UUID getUserId() {
        UUID uuid;

        try {
            String sub = "google" + authorization.getPrincipal().getAttributes().get("sub");
            byte[] bytes = sub.getBytes(StandardCharsets.UTF_8);
            uuid = UUID.nameUUIDFromBytes(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("User Id [{}] was generated from google oauth2 provider", uuid);
        return uuid;
    }
}
