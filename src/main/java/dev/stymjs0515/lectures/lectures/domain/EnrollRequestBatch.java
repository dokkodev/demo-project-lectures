package dev.stymjs0515.lectures.lectures.domain;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "한번에 여러 강의 신청 요청")
public record EnrollRequestBatch(

    @NotNull Long memberId,

    @Schema(description = "신청할 강의 ID 목록")
    @NotEmpty List<Long> lectureIds
) {
}
