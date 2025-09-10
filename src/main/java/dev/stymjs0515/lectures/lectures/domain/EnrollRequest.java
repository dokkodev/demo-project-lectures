package dev.stymjs0515.lectures.lectures.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "단일 강의 신청 요청")
public record EnrollRequest (
    @Schema(description = "(Deprecated) 신청할 강의 ID, 경로 변수의 값을 사용하세요.", deprecated = true)
    Long lectureId,

    @NotNull Long memberId
) {
}
