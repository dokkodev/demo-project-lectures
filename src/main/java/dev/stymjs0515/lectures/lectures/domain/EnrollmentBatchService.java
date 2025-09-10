package dev.stymjs0515.lectures.lectures.domain;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentBatchService {

    private final EnrollmentService enrollmentService;

    public void enrollBatch(EnrollRequestBatch requestBatch) {
        for (Long lectureId : requestBatch.lectureIds()) {
            tryEnroll(requestBatch.memberId(), lectureId);
        }
    }

    private void tryEnroll(long memberId, long lectureId) {
        try {
            enrollmentService.enroll(new EnrollRequest(lectureId, memberId));
        } catch (LectureNotFoundException e) {
            // ignore
        }
    }

}
