package com.knu.bobmate.Account.Controller;

import com.knu.bobmate.Account.Dto.LoginReqDto;
import com.knu.bobmate.Account.Dto.LoginResDto;
import com.knu.bobmate.Account.Service.AccountService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/account")
@Slf4j()
public class AccountController {

    AccountService accountService;

    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResDto> login(@RequestBody @Valid LoginReqDto loginReqDto) {
        return new ResponseEntity<>(new LoginResDto("testtoken"), HttpStatus.OK);
    }

    @GetMapping("/check")
    public String check(@RequestAttribute int userId) {
        log.info(String.valueOf(userId));
        return "Example Here";
    }
}
