package dev.stymjs0515.lectures.lectures.domain;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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
            log.warn("Lecture not found: {}", lectureId);
        } catch (LectureCapacityExceededException e) {
            // ignore
            log.warn("Lecture capacity exceeded: {}", lectureId);
        }
    }

}
