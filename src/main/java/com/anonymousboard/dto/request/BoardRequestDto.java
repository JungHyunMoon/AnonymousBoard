package com.anonymousboard.dto.request;

import com.anonymousboard.validation.ValidPassword;
import com.anonymousboard.validation.ValidUsername;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestDto {
    @ValidUsername
    private String username;
    @ValidPassword
    private String password;
}