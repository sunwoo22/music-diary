package com.example.musicdiary2.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "likes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long diary_id;

    @Column(nullable = false)
    private Long user_id;

    @Builder
    public LikeEntity(Long id, Long diary_id, Long user_id) {
        this.id = id;
        this.diary_id = diary_id;
        this.user_id = user_id;
    }
}
