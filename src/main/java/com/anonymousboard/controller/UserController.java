package com.anonymousboard.controller;

import com.anonymousboard.dto.request.SignRequestDto;
import com.anonymousboard.exception.CustomException;
import com.anonymousboard.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignRequestDto requestDto, HttpServletResponse response) {
        userService.signup(response, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@Valid @RequestBody SignRequestDto requestDto, HttpServletResponse response) throws CustomException {
        userService.signin(response, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }

}