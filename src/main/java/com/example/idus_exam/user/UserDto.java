package com.example.idus_exam.user;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.annotation.Nullable;
import lombok.Getter;

@Getter
public class UserDto {

    @Getter
    public static class SignupRequest {

        @Schema(
                description = "사용자의 이름. 한글과 영문만 입력 가능",
                example = "홍길동",
                required = true
        )
        @NotBlank(message = "Username is required.")
        @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "한글이나 영문만 입력할 수 있습니다.")
        private String username;

        @Schema(
                description = "사용자의 닉네임. 영어 소문자만 입력 가능",
                example = "johnny123",
                required = true
        )
        @NotBlank(message = "Nickname is required.")
        @Pattern(regexp = "^[a-z]*$", message = "영어 소문자만 입력 가능합니다.")
        private String nickname;

        @Schema(
                description = "사용자의 비밀번호. 영문 대소문자, 숫자, 특수문자 포함하고 최소 10자 이상",
                example = "Password123!",
                required = true
        )
        @NotBlank(message = "Password is required.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\$%\\^&\\*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{10,}$",
                message = "Password must contain at least one lowercase letter, one uppercase letter, one number, one special character, and be at least 10 characters long.")
        private String password;

        @Schema(
                description = "사용자의 전화번호",
                example = "01012345678",
                required = true
        )
        @NotBlank(message = "Phone number is required.")
        private int phone;

        @Schema(
                description = "사용자의 이메일 주소",
                example = "example@example.com",
                required = true
        )
        @NotBlank(message = "Email is required.")
        @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid email format.")
        private String email;

        @Schema(
                description = "사용자의 성별. 선택 사항",
                example = "남자"
        )
        @Nullable
        private String gender;
        private Role role;

        public User toEntity(String encodedpassword) {
            return User.builder()
                    .username(username)
                    .nickname(nickname)
                    .password(encodedpassword)
                    .phone(phone)
                    .email(email)
                    .gender(gender)
                    .role(role)
                    .build();
        }
    }
}
