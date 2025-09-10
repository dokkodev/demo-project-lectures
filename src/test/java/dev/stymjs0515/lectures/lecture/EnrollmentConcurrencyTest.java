package dev.stymjs0515.lectures.lecture;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PagedModel;

import dev.stymjs0515.lectures.lectures.domain.EnrollRequestBatch;
import dev.stymjs0515.lectures.lectures.domain.EnrollmentBatchService;
import dev.stymjs0515.lectures.lectures.domain.LectureView;
import dev.stymjs0515.lectures.lectures.domain.Lectures;
import dev.stymjs0515.lectures.lectures.infra.LectureViewController;
import dev.stymjs0515.lectures.lectures.infra.LectureViewSortBy;
import dev.stymjs0515.lectures.member.domain.Member;
import dev.stymjs0515.lectures.member.domain.MemberType;
import dev.stymjs0515.lectures.member.domain.Members;

@SpringBootTest
public class EnrollmentConcurrencyTest {

    @Autowired
    private Members members;

    @Autowired
    private Lectures lectures;

    @Autowired
    private EnrollmentBatchService enrollmentBatchService;

    @Autowired
    private LectureViewController lectureViewController;

    @Test
    void testConcurrentBath() throws InterruptedException{

        // given
        given5LectureWith5CapacityEach();
        given100Students();


        // when
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(100);
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i = 1; i <= 100; i++) {
            final long memberId = i;
            executorService.submit(() -> {
                try {
                    startLatch.await();
                    enrollmentBatchService.enrollBatch(
                        new EnrollRequestBatch(memberId, List.of(1L, 2L, 3L, 4L, 5L))
                    );
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
            });
        }

        startLatch.countDown();
        endLatch.await();
        executorService.shutdown();

        PagedModel<LectureView> result = lectureViewController.getLectureViews(1L, 0, 5, LectureViewSortBy.POPULAR_RATE);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(5);
        for (LectureView lectureView : result.getContent()) {
            assertThat(lectureView.getEnrolledCount()).isEqualTo(5);
        }
    }


    void given5LectureWith5CapacityEach() {
        long lecturerId = members.save(
            Member
                .builder()
                .email("user@example.com")
                .name("User")
                .phone("010-0000-000")
                .passwordHash("hashed_password")
                .type(MemberType.LECTURER)
                .build()
        ).getId();

        for (int i = 1; i <= 5; i++) {
            lectures.save(
                dev.stymjs0515.lectures.lectures.domain.Lecture
                    .builder()
                    .lecturerMemberId(lecturerId)
                    .title("Lecture " + i)
                    .capacity(5)
                    .build());
        }
    }

    void given100Students() {
        for (int i = 1; i <= 100; i++) {
            members.save(
                Member
                    .builder()
                    .email("user" + i + "@example.com")
                    .name("User " + i)
                    .phone("010-0000-000" + i)
                    .passwordHash("hashed_password")
                    .type(MemberType.STUDENT)
                    .build());
        }
    }
}
