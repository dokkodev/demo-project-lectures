package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.stymjs0515.lectures.lectures.domain.LectureQueryService;
import dev.stymjs0515.lectures.lectures.domain.LectureView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "강의 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureViewController {

    private final LectureQueryService lectureQueryService;

    @Operation(
        summary = "강의 조회 통합 API",
        description = """
            현재 열려있는 강의들을 조회합니다. (수강 인원이 가득 차도 표기됩니다.)
            페이징과 정렬이 가능합니다.
            정렬 기준은 다음과 같습니다:
            - RECENT: 최근 생성된 강의 순
            - POPULAR_CNT: 수강 인원 많은 순
            - POPULAR_RATE: 수강 인원 대비 신청 인원 비율이 높은 순
            """
    )
    @GetMapping
    public PagedModel<LectureView> getLectureViews(
        @RequestParam(value = "memberId") Long memberId,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size,
        @RequestParam(value = "sort", defaultValue = "RECENT") LectureViewSortBy sortBy
    ) {
        return new PagedModel<>(lectureQueryService.pageRecent(memberId, page, size, sortBy));
    }



}
