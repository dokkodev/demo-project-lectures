package dev.stymjs0515.lectures.lectures.domain;

public interface Lectures {
    Lecture save(Lecture lecture);
    boolean existsById(long id);
    int tryEnroll(long id);
}
