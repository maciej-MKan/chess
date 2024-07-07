package pl.mkan.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @GetMapping("/")
    public String GetUserInfo() {
        log.info("Get user info access allow");

        return "Access allow";
    }
}
