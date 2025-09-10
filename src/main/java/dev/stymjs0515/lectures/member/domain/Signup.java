package dev.stymjs0515.lectures.member.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Signup(
    @Email @NotBlank String email,
    @NotBlank String phoneNo,
    @NotBlank String name,
    @Size(min = 6, max = 10) String password,
    @NotNull MemberType type
) {
}
