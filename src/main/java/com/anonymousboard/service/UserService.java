package com.anonymousboard.service;

import com.anonymousboard.dto.request.SignRequestDto;
import com.anonymousboard.entity.User;
import com.anonymousboard.exception.CustomException;
import com.anonymousboard.exception.ErrorCode;
import com.anonymousboard.jwt.JwtUtil;
import com.anonymousboard.repository.UserRepository;
import com.anonymousboard.security.UserDetailsImpl;
import com.anonymousboard.security.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    public void signup(SignRequestDto requestDto) throws CustomException {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> DuplicatedUser = userRepository.findByUsername(username);
        if (DuplicatedUser.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_USERNAME);
        }

        User user = new User(username, password);
        userRepository.save(user);
    }

    public String signin(SignRequestDto requestDto, HttpServletResponse response) throws CustomException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getUsername());

        if (passwordEncoder.matches(requestDto.getPassword(), userDetails.getPassword())) {
            return jwtUtil.createToken(userDetails.getUsername());
//            jwtUtil.addJwtToCookie(token, response);
        } else {
            throw new CustomException(ErrorCode.BAD_PARAMETER);
        }
    }

}