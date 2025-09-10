package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.web.bind.annotation.GetMapping;
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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final MemberQueryService memberQueryService;
    private final LectureCreationService lectureCreationService;
    private final EnrollmentService enrollmentService;

    @PostMapping
    public long createLecture(@RequestBody @Valid LectureCreation request) {
        if (!memberQueryService.lecturerExists(request.memberId())) {
            throw new MemberNotFoundException();
        }
        return lectureCreationService.createLecture(request);
    }

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
