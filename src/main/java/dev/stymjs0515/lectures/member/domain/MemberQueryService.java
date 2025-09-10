package dev.stymjs0515.lectures.member.domain;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final Members members;

    public boolean memberExists(long id) {
        return members.existsById(id);
    }

}
