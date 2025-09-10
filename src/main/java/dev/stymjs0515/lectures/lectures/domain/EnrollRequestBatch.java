package dev.stymjs0515.lectures.lectures.domain;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record EnrollRequestBatch(
    long memberId,
    @NotEmpty List<Long> lectureIds
) {
}
