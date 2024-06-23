package pl.mkan.controller.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = GameController.API_PATH)
public class SecurityController {

    @GetMapping(path = "login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Welcome");
    }

    @GetMapping(path = "logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout");
    }
}
