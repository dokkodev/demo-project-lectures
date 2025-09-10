package dev.stymjs0515.lectures.lectures.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long lecturerMemberId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long capacity;

    @Column(nullable = false)
    private Long enrolled;

    @Column(nullable = false)
    private Long price;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;

    @Builder
    public Lecture(long lecturerMemberId, String title, long capacity, long price) {
        this.lecturerMemberId = lecturerMemberId;
        this.title = title;
        this.capacity = capacity;
        this.price = price;
        this.enrolled = 0L;
    }

}
