package pl.mkan.controller.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/")
    public String GetUserInfo(Principal principal) {
        log.info("Get user info access allow");

        OAuth2AuthenticationToken authentication =
                (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                principal.getName()
        );

        log.info("client token : [{}]", client.getAccessToken().getTokenValue());

        return "Access allow for " + principal.getName();
    }
}
