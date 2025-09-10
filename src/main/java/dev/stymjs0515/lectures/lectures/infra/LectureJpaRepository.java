package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import dev.stymjs0515.lectures.lectures.domain.Lecture;
import dev.stymjs0515.lectures.lectures.domain.Lectures;

public interface LectureJpaRepository extends Lectures, JpaRepository<Lecture, Long> {
    boolean existsById(long id);

    @Modifying
    @Query("""
        UPDATE Lecture
        SET enrolled = enrolled + 1
        WHERE id = :id AND enrolled < capacity
    """)
    int tryEnroll(long id);
}
