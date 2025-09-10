package dev.stymjs0515.lectures.lectures.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LectureCapacityExceededException extends RuntimeException {
}
