package com.anonymousboard.service;

import com.anonymousboard.dto.request.BoardRequestDto;
import com.anonymousboard.entity.User;
import com.anonymousboard.exception.CustomException;
import com.anonymousboard.exception.ErrorCode;
import com.anonymousboard.jwt.JwtUtil;
import com.anonymousboard.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signup(HttpServletResponse response, BoardRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 등록
        User user = new User(username, password);
        userRepository.save(user);
    }

    public void signin(HttpServletResponse response, BoardRequestDto requestDto) throws CustomException {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<User> checkUser = userRepository.findByUsername(username);
        if (checkUser.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        User user = checkUser.get();
        log.info("password check : " + passwordEncoder.matches(password, user.getPassword()));
        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtUtil.createToken(username);
            jwtUtil.addJwtToCookie(token, response);
        } else {
            throw new CustomException(ErrorCode.BAD_PARAMETER);
        }
    }

}