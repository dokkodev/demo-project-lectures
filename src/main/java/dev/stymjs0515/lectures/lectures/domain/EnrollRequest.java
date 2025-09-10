package dev.stymjs0515.lectures.lectures.domain;

import jakarta.validation.constraints.NotNull;

public record EnrollRequest (
    Long lectureId,
    @NotNull Long memberId
) {
}
