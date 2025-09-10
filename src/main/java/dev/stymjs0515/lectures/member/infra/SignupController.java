package dev.stymjs0515.lectures.member.infra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.stymjs0515.lectures.member.domain.Signup;
import dev.stymjs0515.lectures.member.domain.SignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class SignupController {

    private final SignupService signupService;

    @PostMapping
    public long signup(@RequestBody @Valid Signup request) {
        return signupService.signup(request);
    }


}
