package com.example.musicdiary2.domain.repository;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    // 전체 데이터 id 역순
//    @Query("select d from diary d order by d.id desc")
    List<DiaryEntity> findAllByOrderByIdDesc();

    // 전체 데이터 id 역순 공개만
    @Query("select d from diary d where d.unopen = 0 order by d.id desc")
    List<DiaryEntity> findOpenPost();

    // 사용자 데이터 id 역순
//    @Query("select d from diary d where writer = :writer order by d.id desc")
    List<DiaryEntity> findAllByWriterOrderByIdDesc(String writer);

    // 제목 keyword 검색
    List<DiaryEntity> findByTitleContaining(String keyword);

    // 사용자 달력 데이터 검색
    @Query("select d from diary d where d.writer = :writer " +
            "and d.createdDate between :endDate and :startDate order by d.id")
    List<DiaryEntity> findByDate(@Param("writer") String writer,
                           @Param("startDate") LocalDate startDate,
                           @Param("endDate") LocalDate endDate);

    // 조회수 증가
    @Transactional
    @Modifying
    @Query("update diary d set d.views = d.views + 1 where d.id = :id")
    void increaseViews(@Param("id") Long id);

    // 추천수 증가
    @Transactional
    @Modifying
    @Query("update diary d set d.likes = d.likes + 1 where d.id = :id")
    void increaseLikes(@Param("id") Long id);

    // 추천수 감소
    @Transactional
    @Modifying
    @Query("update diary d set d.likes = d.likes - 1 where d.id = :id")
    void decreaseLikes(@Param("id") Long id);

    // 글 공개/비공개 변경
    @Transactional
    @Modifying
    @Query("update diary d set d.unopen = :unopen where d.id = :id")
    void updateUnopen(@Param("id") Long id, @Param("unopen") int unopen);

}
