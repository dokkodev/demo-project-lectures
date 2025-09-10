package dev.stymjs0515.lectures.lectures.domain;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureQueryService {

    private final Lectures lectures;

    public boolean lectureExists(long id) {
        return lectures.existsById(id);
    }
}
