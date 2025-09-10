package dev.stymjs0515.lectures.lectures.domain;

public interface Enrollments {
    Enrollment save(Enrollment enrollment);
    boolean existsByLectureIdAndMemberId(long lectureId, long memberId);
}
