package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.stymjs0515.lectures.lectures.domain.LectureCreation;
import dev.stymjs0515.lectures.lectures.domain.LectureCreationService;
import dev.stymjs0515.lectures.member.domain.MemberQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureCreationService lectureCreationService;
    private final MemberQueryService memberQueryService;

    @PostMapping
    public long createLecture(@RequestBody @Valid LectureCreation request) {
        if (!memberQueryService.memberExists(request.lecturerId())) {
            throw new LecturerNotFoundException();
        }
        return lectureCreationService.createLecture(request);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class LecturerNotFoundException extends RuntimeException {
        public LecturerNotFoundException() {}
    }

}
