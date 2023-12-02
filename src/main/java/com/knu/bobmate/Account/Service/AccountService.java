package com.knu.bobmate.Account.Service;

import com.knu.bobmate.Account.Dto.LoginResDto;
import com.knu.bobmate.Account.Dto.ProfileResDto;
import com.knu.bobmate.Account.Exception.Exceptions.LoginException;
import com.knu.bobmate.Account.Filter.LoginCheckFilter;
import com.knu.bobmate.Account.Repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String login(String id, String password) {
        return accountRepository.login(id, password);
    }

    public void register(String id, String password, String nickname, String email) { accountRepository.register(id, password, nickname, email); }

    public ProfileResDto profile(int userId) {
        ProfileResDto profileResDto = accountRepository.profile(userId);
        return profileResDto;
    }
}
