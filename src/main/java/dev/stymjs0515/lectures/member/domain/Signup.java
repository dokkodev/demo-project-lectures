package dev.stymjs0515.lectures.member.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "회원 가입 요청")
public record Signup(

    @Schema(description = "이메일은 고유해야 합니다.")
    @Email @NotBlank String email,

    @NotBlank String phoneNo,

    @NotBlank String name,

    @Schema(description = """
        비밀번호는 최소 6자 이상 10자 이하여야 합니다.
        비밀번호는 소문자, 대문자, 숫자 중 최소 두가지 이상 조합이 필요합니다.
        """)
    @Size(min = 6, max = 10) String password,

    @Schema(description = "회원의 종류에는 수강생과 강사가 있습니다.")
    @NotNull MemberType type
) {
}
