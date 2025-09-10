package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.stymjs0515.lectures.lectures.domain.EnrollRequestBatch;
import dev.stymjs0515.lectures.lectures.domain.EnrollmentBatchService;
import dev.stymjs0515.lectures.lectures.domain.MemberNotFoundException;
import dev.stymjs0515.lectures.member.domain.MemberQueryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final MemberQueryService memberQueryService;
    private final EnrollmentBatchService enrollmentBatchService;

    @PostMapping
    public void enroll(
        @RequestBody EnrollRequestBatch request
    ) {
        if (!memberQueryService.memberExists(request.memberId())) {
            throw new MemberNotFoundException();
        }
        enrollmentBatchService.enrollBatch(request);
    }

}
