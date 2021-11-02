package com.example.musicdiary2.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "diary")
@NoArgsConstructor
@Getter
@Table(name = "diary")
public class DiaryEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String singer;

    @Column(length = 200)
    private String imgSrc;

    @Column(length = 1)
    private int mood; // 1: 기쁨 2: 편안 3: 우울 4: 화남

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 1, columnDefinition = "int default 0")
    private int unopen; // 0: 공개 / 1: 비공개

    @Column(length = 6, columnDefinition = "int default 0")
    private int views; // 조회수

    @Builder
    public DiaryEntity(Long id, String writer, String title, String singer,
                       String imgSrc, int mood, String content,
                       int unopen, int views) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.singer = singer;
        this.imgSrc = imgSrc;
        this.mood = mood;
        this.content = content;
        this.unopen = unopen;
        this.views = views;
    }

    @Override
    public String toString() {
        return "DiaryEntity{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", singer='" + singer + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", mood=" + mood +
                ", content='" + content + '\'' +
                ", open=" + unopen +
                ", views=" + views +
                '}';
    }


}
