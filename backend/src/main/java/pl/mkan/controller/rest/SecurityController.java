package pl.mkan.controller.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkan.controller.dto.LoginRedirectDTO;
import pl.mkan.controller.dto.UserDTO;
import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.service.UserService;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/security")
public class SecurityController {

    private final UserService userService;

    @Value("${server.front}")
    private String frontUrl;

    @GetMapping(path = "/login")
    public ResponseEntity<LoginRedirectDTO> loginPage(HttpServletRequest request) {
        String redirectUrl = frontUrl + "/";
        log.info(request.getRequestURL().toString());
        log.info("redirecting to {}", redirectUrl);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginRedirectDTO("Please login"));
    }

    @GetMapping(path = "/login-success")
    public void login(@AuthenticationPrincipal OAuth2User principal, HttpServletResponse response) throws IOException {
        String name = principal.getAttribute("name");
        Optional<PieceColor> userColor = Optional.empty();
        log.info("User [{}] logged in", name);
        boolean newUser = userService.saveIfNewUser(new UserDTO(name));
        if (!newUser) {
            userColor = userService.getUserColor();
        }
        String playerColor = userColor.map(Enum::name).orElse("null");
        log.info("User default color [{}]", playerColor);
        response.sendRedirect(frontUrl + "/login_success");
    }

    @GetMapping("/logout")
    public String logout(
            @AuthenticationPrincipal OAuth2User user,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        log.info("User [{}] log out", user.getName());
        return "Bye " + user.getAttribute("name");
    }
}
