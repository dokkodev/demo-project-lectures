package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.stymjs0515.lectures.lectures.domain.Lecture;
import dev.stymjs0515.lectures.lectures.domain.LectureView;
import dev.stymjs0515.lectures.lectures.domain.LectureViews;

public interface LectureViewJpaRepository extends LectureViews, JpaRepository<Lecture, Long> {

    @Query(
        value = """
            SELECT new dev.stymjs0515.lectures.lectures.domain.LectureView(
                l.id,
                l.title,
                l.price,
                m.name,
                l.capacity,
                l.enrolled,
                (100.0 * l.enrolled / l.capacity),
                e.id
            )
                
            FROM Lecture l
            JOIN Member m
                ON l.lecturerMemberId = m.id
            LEFT JOIN Enrollment e
                ON l.id = e.lectureId
                AND e.memberId = :memberId
        """,
        countQuery = """
            SELECT COUNT(*) from Lecture
        """
    )
    Page<LectureView> page(Long memberId, Pageable pageable);

    @Query(
        value = """
            SELECT new dev.stymjs0515.lectures.lectures.domain.LectureView(
                l.id,
                l.title,
                l.price,
                m.name,
                l.capacity,
                l.enrolled,
                (100.0 * l.enrolled / l.capacity),
                e.id
            )
            FROM Lecture l
            JOIN Member m
                ON l.lecturerMemberId = m.id
            LEFT JOIN Enrollment e
                ON l.id = e.lectureId
                AND e.memberId = :memberId
            ORDER BY 7 DESC, l.id DESC
        """,
        countQuery = """
            SELECT COUNT(*) from Lecture
        """
    )
    Page<LectureView> pagePopularRate(Long memberId, Pageable pageable);


}
