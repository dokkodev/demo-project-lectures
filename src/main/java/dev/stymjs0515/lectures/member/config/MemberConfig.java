package dev.stymjs0515.lectures.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.stymjs0515.lectures.member.domain.SignupService;
import dev.stymjs0515.lectures.member.infra.SignupController;

@Configuration
public class MemberConfig {

    @Bean
    SignupController signupController(SignupService signupService) {
        return new SignupController(signupService);
    }

}
