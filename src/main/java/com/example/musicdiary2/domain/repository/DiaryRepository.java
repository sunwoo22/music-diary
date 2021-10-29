package com.example.musicdiary2.domain.repository;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

//    @Query("select d from diary d order by d.id desc")
    List<DiaryEntity> findAllByOrderByIdDesc();

//    @Query("select d from diary d where writer = :writer order by d.id desc")
    List<DiaryEntity> findAllByWriterOrderByIdDesc(String writer);

    List<DiaryEntity> findByTitleContaining(String keyword);

    @Query("select d from Diary d where d.created_date between :createdDate and :createdDate1")
    DiaryEntity findByCreatedDate(@Param("createdDate") LocalDateTime createdDate,
                                  @Param("createdDate1")LocalDateTime createdDate1);

    @Query("select d from Diary d where d.title = :title")
    DiaryEntity findByTitle1(@Param("title") String title);
}
