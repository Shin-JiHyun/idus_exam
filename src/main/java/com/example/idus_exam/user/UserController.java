package com.example.idus_exam.user;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class UserController {
    private final UserService userService;
    private final TokenBlackListService tokenBlackListService;

    @Operation(summary = "회원가입", description = "회원가입 api입니다." )
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserDto.SignupRequest signupRequest) {
        userService.create(signupRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value = "ATOKEN", required = false) String token) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token found");
        }

        tokenBlackListService.blacklistToken(token); // 토큰 블랙리스트 저장
        return ResponseEntity.ok("Logged out successfully");
    }
    @GetMapping("/detail")
    public ResponseEntity<UserDto.UserResponse> detail(@AuthenticationPrincipal User user) {
       UserDto.UserResponse dto =  userService.getUserDetail(user);
       return ResponseEntity.ok(dto);
    }

}
