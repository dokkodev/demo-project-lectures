package dev.stymjs0515.lectures.lectures.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.stymjs0515.lectures.lectures.domain.Lecture;
import dev.stymjs0515.lectures.lectures.domain.Lectures;

public interface LectureJpaRepository extends Lectures, JpaRepository<Lecture, Long> {
    boolean existsById(long id);
}
