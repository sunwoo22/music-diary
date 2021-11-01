package com.example.musicdiary2.domain.repository;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

//    @Query("select d from diary d order by d.id desc")
    List<DiaryEntity> findAllByOrderByIdDesc();

//    @Query("select d from diary d where writer = :writer order by d.id desc")
    List<DiaryEntity> findAllByWriterOrderByIdDesc(String writer);

    List<DiaryEntity> findByTitleContaining(String keyword);

    @Query("select d from diary d where d.writer = :writer " +
            "and d.createdDate between :endDate and :startDate order by d.id")
    List<DiaryEntity> findByDate(@Param("writer") String writer,
                           @Param("startDate") LocalDate startDate,
                           @Param("endDate") LocalDate endDate);
}
