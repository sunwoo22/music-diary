package com.example.musicdiary2.dto;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime createdDate;
//    private LocalDateTime modifiedDate;

    @Builder
//    public DiaryDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
    public DiaryDto(Long id, String writer, String title, String singer,
                    String imgSrc, int mood, String content, LocalDateTime createdDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.singer = singer;
        this.imgSrc = imgSrc;
        this.mood = mood;
        this.content = content;
        this.createdDate = createdDate;
//        this.modifiedDate = modifiedDate;
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
                .build();
        return diaryEntity;
    }

}
