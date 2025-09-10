package dev.stymjs0515.lectures.lectures.domain;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureCreationService {

    private final Lectures lectures;

    @Transactional
    public long createLecture(LectureCreation lectureCreation) {
        Lecture lecture = lectures.save(of(lectureCreation));
        return lecture.getId();
    }

    Lecture of(LectureCreation lectureCreation) {
        return Lecture.builder()
                      .lecturerId(lectureCreation.memberId())
                      .title(lectureCreation.title())
                      .capacity(lectureCreation.capacity())
                      .price(lectureCreation.price())
                      .build();
    }


}
