package pl.mkan.controller.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkan.controller.dto.UserDTO;

import java.security.Principal;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping
    public ResponseEntity<UserDTO> GetUserInfo(Principal principal, @AuthenticationPrincipal OAuth2User user) {
        log.info("Get user info access allow");

        OAuth2AuthenticationToken authentication =
                (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                principal.getName()
        );

        log.info("client token : [{}]", client.getAccessToken().getTokenValue());

        return ResponseEntity.ok(new UserDTO(user.getAttribute("name")));
    }
}
