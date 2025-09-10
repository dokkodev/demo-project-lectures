package dev.stymjs0515.lectures.member.domain;

public interface Members {
    boolean existsByEmail(String email);
    Member save(Member member);
}
