package com.example.musicdiary2.service;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import com.example.musicdiary2.domain.entity.LikeEntity;
import com.example.musicdiary2.domain.repository.LikeRepository;
import com.example.musicdiary2.dto.DiaryDto;
import com.example.musicdiary2.dto.LikeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LikeService {

    private LikeRepository likeRepository;

    // 좋아요 누르기
    @Transactional
    public Long pushLike(Long diary_id, Long user_id) {
        LikeDto likeDto = new LikeDto();
        likeDto.setDiary_id(diary_id);
        likeDto.setUser_id(user_id);
        return likeRepository.save(likeDto.toEntity()).getId();
    }

    // 좋아요 취소하기
    @Transactional
    public void cancelLike(Long diary_id, Long user_id) {
        likeRepository.cancelLike(diary_id, user_id);
    }

    // 좋아요 개수
    public int countLikeNum(Long diary_id) {
        return likeRepository.countLikeNum(diary_id);
    }

    // 좋아요 눌렀는지 확인
    public boolean checkLike(Long diary_id, Long user_id) {
        if (likeRepository.checkLike(diary_id, user_id) == 1) {
            return true;
        } else {
            return false;
        }
    }



    // 엔티티를 디티오로 바꾸기
    private LikeDto convertEntityToDto(LikeEntity likeEntity) {
        return LikeDto.builder()
                .id(likeEntity.getId())
                .diary_id(likeEntity.getDiary_id())
                .user_id(likeEntity.getUser_id())
                .build();
    }


}
