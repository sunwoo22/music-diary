package com.example.musicdiary2.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "diary")
public class DiaryEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String singer;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
//    public DiaryEntity(Long id, String title, String content, String writer) {
    public DiaryEntity(Long id, String writer, String title,
                       String singer, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.singer = singer;
        this.content = content;
    }

}
