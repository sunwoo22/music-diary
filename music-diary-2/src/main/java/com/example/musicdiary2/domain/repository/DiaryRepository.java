package com.example.musicdiary2.domain.repository;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
}
