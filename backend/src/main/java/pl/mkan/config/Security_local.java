package pl.mkan.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Profile("local")
public class Security_local {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                            "/api/game/history"
                    ).authenticated();
                    auth.requestMatchers(
                            "/api/**",
                            "/api/game/**",
                            "/login/**",
                            "/security/login",
                            "/oauth2/**",
                            "/error/**",
                            "/error**",
                            "/security/logout",
                            "/h2-console/**",
                            "/h2-console**"
                    ).permitAll();
                    auth.anyRequest().authenticated();
                })
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/**"))
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
//                .sessionManagement(sessionManagement -> sessionManagement
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
                .oauth2Login(
                        oauth2 -> oauth2
                                .loginPage("/security/login")
                                .defaultSuccessUrl("/security/login-success", true)
                                .failureUrl("/login?error=true")
                );

        return http.build();
    }
}
