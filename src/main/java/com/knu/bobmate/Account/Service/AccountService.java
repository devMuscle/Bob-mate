package com.knu.bobmate.Account.Service;

import com.knu.bobmate.Account.Dto.LoginResDto;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public LoginResDto login() {
        return new LoginResDto();
    }
}
