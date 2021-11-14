package com.example.musicdiary2.dto;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DiaryDto {
    private Long id;
    private String writer;
    private String title;
    private String singer;
    private String imgSrc;
    private int mood; // 1: 기쁨 2: 편안 3: 우울 4: 화남
    private String content;
    private LocalDate createdDate;
//    private LocalDateTime modifiedDate;
    private int unopen; // 글 공개 / 비공개
    private int views; // 조회수
    private int likes; // 추천수

    @Builder
    public DiaryDto(Long id, String writer, String title, String singer,
                    String imgSrc, int mood, String content, LocalDate createdDate,
                    int unopen, int views, int likes) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.singer = singer;
        this.imgSrc = imgSrc;
        this.mood = mood;
        this.content = content;
        this.createdDate = createdDate;
//        this.modifiedDate = modifiedDate;
        this.unopen = unopen;
        this.views = views;
        this.likes = likes;
    }

    public DiaryEntity toEntity(){
        DiaryEntity diaryEntity = DiaryEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .singer(singer)
                .imgSrc(imgSrc)
                .mood(mood)
                .content(content)
                .unopen(unopen)
                .views(views)
                .likes(likes)
                .build();
        return diaryEntity;
    }

}
