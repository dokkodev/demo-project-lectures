package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.stymjs0515.lectures.lectures.domain.Enrollment;
import dev.stymjs0515.lectures.lectures.domain.Enrollments;

public interface EnrollmentJpaRepository extends Enrollments, JpaRepository<Enrollment, Long> {
    boolean existsByLectureIdAndMemberId(long lectureId, long memberId);
}
