package dev.stymjs0515.lectures.member.infra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.stymjs0515.lectures.member.domain.Signup;
import dev.stymjs0515.lectures.member.domain.SignupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "멤버 인증")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class SignupController {

    private final SignupService signupService;

    @Operation(
        summary = "회원 가입",
        description = """
            이메일과 이름을 받아서 회원을 생성하고, 생성된 회원의 ID를 반환합니다.
            1. 이메일은 고유해야 합니다.
            2. 비밀번호는 최소 6자 이상 10자 이하여야 합니다.
            3. 비밀번호는 소문자, 대문자, 숫자 중 최소 두가지 이상 조합이 필요합니다.
            4. 회원의 종류에는 수강생과 강사가 있습니다.
            """
    )
    @PostMapping
    public long signup(@RequestBody @Valid Signup request) {
        return signupService.signup(request);
    }


}
