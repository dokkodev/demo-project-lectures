package dev.stymjs0515.lectures.lectures.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LectureCreation (
    @NotNull
    Long memberId,

    @NotBlank
    String title,

    @Positive
    long capacity,

    @Positive
    long price
) {
}
