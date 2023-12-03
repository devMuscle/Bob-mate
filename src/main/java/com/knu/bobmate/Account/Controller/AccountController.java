package com.knu.bobmate.Account.Controller;

import com.knu.bobmate.Account.Dto.LoginReqDto;
import com.knu.bobmate.Account.Dto.LoginResDto;
import com.knu.bobmate.Account.Dto.ProfileResDto;
import com.knu.bobmate.Account.Dto.RegisterReqDto;
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

    private final AccountService accountService;

    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@RequestBody @Valid LoginReqDto loginReqDto) {
        String token = accountService.login(loginReqDto.getId(), loginReqDto.getPassword());
        return new ResponseEntity<>(new LoginResDto(token), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid RegisterReqDto registerReqDto) {
        accountService.register(registerReqDto.getId(), registerReqDto.getPassword(), registerReqDto.getNickname(), registerReqDto.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/profile")
    public ResponseEntity<ProfileResDto> profile(@RequestAttribute int userId) {
        ProfileResDto profileResDto = accountService.profile(userId);
        return new ResponseEntity<>(profileResDto, HttpStatus.OK);
    }
}
