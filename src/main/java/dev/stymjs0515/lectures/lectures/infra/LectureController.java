package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.stymjs0515.lectures.lectures.domain.EnrollRequest;
import dev.stymjs0515.lectures.lectures.domain.EnrollmentService;
import dev.stymjs0515.lectures.lectures.domain.LectureCreation;
import dev.stymjs0515.lectures.lectures.domain.LectureCreationService;
import dev.stymjs0515.lectures.lectures.domain.MemberNotFoundException;
import dev.stymjs0515.lectures.member.domain.MemberQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "강의")
@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final MemberQueryService memberQueryService;
    private final LectureCreationService lectureCreationService;
    private final EnrollmentService enrollmentService;

    @Operation(
        summary = "강의 생성",
        description = """
            강의를 생성합니다. 강의 생성자는 반드시 강사여야 합니다.
            강의 생성에 성공하면 생성된 강의 ID를 반환합니다.
            """
    )
    @PostMapping
    public long createLecture(@RequestBody @Valid LectureCreation request) {
        if (!memberQueryService.lecturerExists(request.memberId())) {
            throw new MemberNotFoundException();
        }
        return lectureCreationService.createLecture(request);
    }

    @Operation(
        summary = "단일 강의 신청",
        description = """
            특정 강의를 한명의 멤버가 신청합니다. 강의 신청에 성공하면 생성된 수강 신청 ID를 반환합니다.
            """
    )
    @PostMapping("/{lectureId}/enrollments")
    public Long enroll(
        @RequestBody @Valid EnrollRequest request,
        @PathVariable("lectureId") Long lectureId
    ) {
        if (!memberQueryService.memberExists(request.memberId())) {
            throw new MemberNotFoundException();
        }

        return enrollmentService.enroll(new EnrollRequest(lectureId, request.memberId()));
    }

}
