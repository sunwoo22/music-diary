package com.example.musicdiary2.domain.repository;

import com.example.musicdiary2.domain.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    // 좋아요 개수
    @Query("select count(l) from likes l where l.diary_id = :diary_id")
    int countLikeNum(@Param("diary_id") Long diary_id);

    // 좋아요 취소하기
    @Transactional
    @Modifying
    @Query("delete from likes l where l.diary_id = :diary_id and l.user_id = :user_id")
    void cancelLike(@Param("diary_id") Long diary_id, @Param("user_id") Long user_id);

    // 좋아요 눌렀는지 확인
    @Query("select count(l) from likes l " +
            "where l.diary_id = :diary_id and l.user_id = :user_id")
    int checkLike(@Param("diary_id") Long diary_id, @Param("user_id") Long user_id);
}
