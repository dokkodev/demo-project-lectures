package dev.stymjs0515.lectures.member.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.stymjs0515.lectures.member.domain.Member;
import dev.stymjs0515.lectures.member.domain.MemberType;
import dev.stymjs0515.lectures.member.domain.Members;

public interface MemberJpaRepository extends Members, JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    boolean existsById(long id);
    boolean existsByIdAndType(long id, MemberType type);
}
