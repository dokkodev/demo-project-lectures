package dev.stymjs0515.lectures.lectures.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Schema(description = "유저 화면 강의 조회 모델")
@Value
public class LectureView {
    Long lectureId;
    String title;
    Long price;
    String lecturerName;
    Long capacity;
    Long enrolledCount;
    @Schema(description = "수강 인원 대비 신청 인원 비율 (0~100 사이의 정수)")
    int enrollRate;
    @Schema(description = "현재 유저가 해당 강의를 신청했는지 여부")
    boolean isEnrolled;

    public LectureView(
        Long lectureId,
        String title,
        Long price,
        String lecturerName,
        Long capacity,
        Long enrolledCount,
        Double enrollRate,
        Long enrolled
    ) {
        this.lectureId = lectureId;
        this.title = title;
        this.price = price;
        this.lecturerName = lecturerName;
        this.capacity = capacity;
        this.enrolledCount = enrolledCount;
        this.enrollRate = Math.round(enrollRate.floatValue());
        this.isEnrolled = enrolled != null && enrolled > 0;
    }
}
