package com.example.musicdiary2.dto;

import com.example.musicdiary2.domain.entity.LikeEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeDto {

    private Long id;
    private Long diary_id;
    private Long user_id;

    @Builder
    public LikeDto(Long id, Long diary_id, Long user_id) {
        this.diary_id = diary_id;
        this.user_id = user_id;
    }

    public LikeEntity toEntity(){
        LikeEntity likeEntity = LikeEntity.builder()
                .id(id)
                .diary_id(diary_id)
                .user_id(user_id)
                .build();
        return likeEntity;
    }

}
