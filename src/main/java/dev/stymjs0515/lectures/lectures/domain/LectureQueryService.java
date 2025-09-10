package dev.stymjs0515.lectures.lectures.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import dev.stymjs0515.lectures.lectures.infra.LectureViewSortBy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureQueryService {

    private final Lectures lectures;
    private final LectureViews lectureViews;

    public boolean lectureExists(long id) {
        return lectures.existsById(id);
    }

    public Page<LectureView> pageRecent(Long memberId, int page, int size, LectureViewSortBy sortBy) {
        return switch (sortBy) {
            case RECENT -> lectureViews.page(memberId, PageRequest.of(page, size, Sort.by(Order.desc("created_at"))));
            case POPULAR_CNT -> lectureViews.page(memberId, PageRequest.of(page, size, Sort.by(Order.desc("enrolled"), Order.desc("id"))));
            case POPULAR_RATE -> lectureViews.pagePopularRate(memberId, PageRequest.of(page, size));
        };
    }
}
