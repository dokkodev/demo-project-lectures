package dev.stymjs0515.lectures.lectures.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LectureViews {
    Page<LectureView> page(Long memberId, Pageable pageable);
    Page<LectureView> pagePopularRate(Long memberId, Pageable pageable);
}
