package dev.stymjs0515.lectures.lectures.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "강의 생성 요청")
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
