package dev.stymjs0515.lectures.lectures.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LectureNotFoundException extends RuntimeException {
    public LectureNotFoundException() {}
}
