package dev.stymjs0515.lectures.member.domain;

public interface Members {
    boolean existsByEmail(String email);
    boolean existsById(long id);
    Member save(Member member);
}
