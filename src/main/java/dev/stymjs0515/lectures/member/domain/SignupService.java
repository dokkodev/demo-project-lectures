package dev.stymjs0515.lectures.member.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final Members members;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public long signup(Signup signup) {
        if (members.existsByEmail(signup.email())) {
            throw new EmailAlreadyInUseException();
        }

        if (!PasswordPolicy.twoOfThree(signup.password())) {
            throw new WeakPasswordException();
        }

        final String encodedPassword = encodePassword(signup.password());
        Member member = members.save(of(signup, encodedPassword));
        return member.getId();
    }

    static Member of(Signup signup, String passwordHash) {
        return Member
            .builder()
            .email(signup.email())
            .name(signup.name())
            .passwordHash(passwordHash)
            .phone(signup.phoneNo())
            .type(signup.type())
            .build();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
