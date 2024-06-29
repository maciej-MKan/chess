package pl.mkan.controller.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/security")
public class SecurityController {

    @GetMapping(path = "/login")
    public ResponseEntity<String> login(@AuthenticationPrincipal OAuth2User principal) {
        log.info("User [{}] login", principal.getName());
        OAuth2AuthenticationToken authentication =
                (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            log.info(authentication.getAuthorizedClientRegistrationId());
        }
        return ResponseEntity.ok("Welcome " + principal.getAttribute("name"));
    }
  
    @GetMapping("/logout")
    public String logout(
            @AuthenticationPrincipal OAuth2User user,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        log.info("User [{}] log out", user.getName());
        return "Bye " + user.getAttribute("name");
    }
}
