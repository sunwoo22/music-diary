package com.example.musicdiary2.domain.repository;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import com.example.musicdiary2.dto.DiaryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiaryRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    void diaryAddTest() {
        DiaryEntity diary1 = DiaryEntity.builder()
                .writer("newaaa@example.com")
                .title("aaa")
                .singer("aaa")
                .content("aaaaaa")
                .build();
        DiaryEntity diary2 = DiaryEntity.builder()
                .writer("newbbb@example.com")
                .title("bbb")
                .singer("bbb")
                .content("bbbbbb")
                .build();


        diaryRepository.save(diary1);
        diaryRepository.save(diary2);

        diaryRepository.findAll().forEach(System.out::println);

    }

    @Test
    void diaryFindByWriterTest() {
        DiaryEntity diary1 = DiaryEntity.builder()
                .writer("newaaa@example.com")
                .title("aaa")
                .singer("aaa")
                .content("aaaaaa")
                .build();
        DiaryEntity diary2 = DiaryEntity.builder()
                .writer("newbbb@example.com")
                .title("bbb")
                .singer("bbb")
                .content("bbbbbb")
                .build();


        diaryRepository.save(diary1);
        diaryRepository.save(diary2);

        diaryRepository.findAllByWriter("newaaa@example.com").forEach(System.out::println);

    }

}