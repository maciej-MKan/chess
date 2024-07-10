package pl.mkan.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Component
public class BarerTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        Optional<Cookie> tokenCookie = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> "token".equals(cookie.getName()))
                .findFirst();

        if (tokenCookie.isPresent()) {
            // Token is present in cookies, print it to console
//            log.info("Token found: [{}]", tokenCookie.get().getValue());
            Authentication oauth2auth = SecurityContextHolder.getContext().getAuthentication();
            log.info("oauth2 authentication: {}", oauth2auth);
//            Authentication authentication = Optional.ofNullable(oauth2auth)
//                    .orElse(new OAuth2AuthenticationToken(new DefaultOAuth2User(null, Map.of("name", "user name", "userId", "user id"), "name"), null, null));
//            log.info("authentication {}", authentication);
            oauth2auth.setAuthenticated(true);
//            SecurityContextHolder.getContext().setAuthentication();
        } else {
            // Token is not present
            log.info("Token not found in cookies");
        }
        filterChain.doFilter(request, response);
    }
}
