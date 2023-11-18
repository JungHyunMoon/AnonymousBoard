package com.anonymousboard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 글이 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저가 없습니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB에러"),
    BAD_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 password 입니다.")
    ;
    private HttpStatus status;
    private String message;

}
