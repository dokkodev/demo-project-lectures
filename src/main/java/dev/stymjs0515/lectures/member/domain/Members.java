package dev.stymjs0515.lectures.member.domain;

public interface Members {
    boolean existsByEmail(String email);
    boolean existsById(long id);
    boolean existsByIdAndType(long id, MemberType type);
    Member save(Member member);
}
