package com.knu.bobmate.Account.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Slf4j()
public class AccountController {
    @GetMapping("/login")
    public String login() {
        return "Example Here";
    }

    @GetMapping("/check")
    public String check(@RequestAttribute int userId) {
        log.info(String.valueOf(userId));
        return "Example Here";
    }
}
