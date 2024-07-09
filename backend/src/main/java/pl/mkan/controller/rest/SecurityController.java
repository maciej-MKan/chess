package pl.mkan.controller.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkan.controller.dto.UserDTO;
import pl.mkan.service.UserService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/security")
public class SecurityController {

    private final UserService userService;

    @Value("${server.front}")
    private String frontUrl;

    @GetMapping(path = "/login")
    public void loginPage(HttpServletResponse response) throws IOException {
        String redirectUrl = frontUrl + "/login";
        log.info("redirecting to {}", redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    @GetMapping(path = "/login-success")
    public void login(@AuthenticationPrincipal OAuth2User principal, @AuthenticationPrincipal OAuth2AuthenticationToken idToken, HttpServletResponse response) throws IOException {
        String name = principal.getAttribute("name");
        log.info("User [{}] logged in", name);
        log.info("IdToken [{}]", idToken);
        userService.saveIfNewUser(new UserDTO(name));
//        response.addCookie(new Cookie("Authorization", "Bearer " + "token"));
        response.sendRedirect(frontUrl + "/");
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
