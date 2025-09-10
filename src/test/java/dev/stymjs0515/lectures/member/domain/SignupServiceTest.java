package dev.stymjs0515.lectures.member.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import dev.stymjs0515.lectures.member.domain.SignupService.EmailAlreadyInUseException;
import dev.stymjs0515.lectures.member.domain.SignupService.WeakPasswordException;

class SignupServiceTest {

    Members members = mock(Members.class);

    SignupService cut = new SignupService(members);

    @Test
    void signupSuccess() {
        // given
        final long savedMemberId = 1L;
        givenMemberSaveSuccessWithId(savedMemberId);

        // when
        long result = cut.signup(new Signup(
            "asdf@asdf.com",
            "010-1234-5678",
            "James",
            "Password1",
            MemberType.STUDENT
        ));

        // then
        assertEquals(savedMemberId, result);
    }

    @Test
    void emailInUse() {
        // given
        final String usedEmail = "asdf@asdf.com";
        givenEmailAlreadyInUse(usedEmail);

        // when
        Executable executable = () -> cut.signup(
            new Signup(
                usedEmail,
                "010-1234-5678",
                "James",
                "asdfasdf",
                MemberType.STUDENT
            ));

        // then
        assertThrows(EmailAlreadyInUseException.class, executable);
    }

    @Test
    void passwordWeak() {
        // given
        final long savedMemberId = 2L;
        givenMemberSaveSuccessWithId(savedMemberId);

        // when
        Executable executable = () -> cut.signup(
            new Signup(
                "asdf@asdf.com",
                "010-1234-5678",
                "James",
                "asdfasdf",
                MemberType.STUDENT
        ));

        // then
        assertThrows(WeakPasswordException.class, executable);
    }

    void givenMemberSaveSuccessWithId(long id) {
        Member member = mock(Member.class);
        doReturn(id).when(member).getId();
        doReturn(member).when(members).save(any());
    }

    void givenEmailAlreadyInUse(String email) {
        doReturn(true).when(members).existsByEmail(email);
    }

}