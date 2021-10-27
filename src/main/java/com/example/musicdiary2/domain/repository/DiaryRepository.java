package com.example.musicdiary2.domain.repository;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    List<DiaryEntity> findByTitleContaining(String keyword);

}
