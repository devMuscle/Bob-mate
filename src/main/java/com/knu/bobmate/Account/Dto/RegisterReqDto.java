package com.knu.bobmate.Account.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReqDto {
    @NotNull
    private String id;
    @NotNull
    private String password;
    @NotNull
    private String nickname;
    @NotNull
    private String email;
}