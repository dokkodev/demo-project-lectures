package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.stymjs0515.lectures.lectures.domain.LectureQueryService;
import dev.stymjs0515.lectures.lectures.domain.LectureView;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureViewController {

    private final LectureQueryService lectureQueryService;

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
