package dev.stymjs0515.lectures.lectures.domain;

import lombok.Value;

@Value
public class LectureView {
    Long lectureId;
    String title;
    Long price;
    String lecturerName;
    Long capacity;
    Long enrolledCount;
    int enrollRate;
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
