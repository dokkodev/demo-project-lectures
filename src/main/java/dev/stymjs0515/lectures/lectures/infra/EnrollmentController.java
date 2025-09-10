package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.stymjs0515.lectures.lectures.domain.EnrollRequestBatch;
import dev.stymjs0515.lectures.lectures.domain.EnrollmentBatchService;
import dev.stymjs0515.lectures.lectures.domain.MemberNotFoundException;
import dev.stymjs0515.lectures.member.domain.MemberQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "강의 신청")
@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final MemberQueryService memberQueryService;
    private final EnrollmentBatchService enrollmentBatchService;

    @Operation(
        summary = "한번에 여러 강의 신청",
        description = """
            한명의 멤버가 여러 강의를 신청할 수 있습니다. 하나가 실패하더라도 나머지는 성공할 수 있습니다.
            실제 성공했는지 확인하려면 강의 API를 다시 조회하여 확인해야 합니다.
            """
    )
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
