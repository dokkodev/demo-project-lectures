package dev.stymjs0515.lectures.lectures.domain;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final Enrollments enrollments;
    private final Lectures lectures;
    private final LectureQueryService lectureQueryService;

    @Transactional
    public Long enroll(EnrollRequest request) {
        if (!lectureQueryService.lectureExists(request.lectureId())) {
            throw new LectureNotFoundException();
        }

        if (enrollments.existsByLectureIdAndMemberId(request.lectureId(), request.memberId())) {
            return null;
        }

        int enrolled = lectures.tryEnroll(request.lectureId());
        if (enrolled == 0) {
            throw new LectureCapacityExceededException();
        }

        Enrollment enrollment = enrollments.save(of(request));
        return enrollment.getId();
    }

    Enrollment of(EnrollRequest request) {
        return Enrollment.builder()
                         .lectureId(request.lectureId())
                         .memberId(request.memberId())
                         .build();
    }

}
