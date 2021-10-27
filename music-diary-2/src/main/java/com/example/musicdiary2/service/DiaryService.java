package com.example.musicdiary2.service;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import com.example.musicdiary2.domain.repository.DiaryRepository;
import com.example.musicdiary2.dto.DiaryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiaryService {
    private DiaryRepository diaryRepository;

    @Transactional
    public List<DiaryDto> getDiaryList() {
        List<DiaryEntity> diaryEntities = diaryRepository.findAll();
        List<DiaryDto> diaryDtoList = new ArrayList<>();

        for (DiaryEntity diaryEntity : diaryEntities) {
            DiaryDto diaryDto = DiaryDto.builder()
                    .id(diaryEntity.getId())
                    .writer(diaryEntity.getWriter())
                    .title(diaryEntity.getTitle())
                    .singer(diaryEntity.getSinger())
                    .content(diaryEntity.getContent())
                    .createdDate(diaryEntity.getCreatedDate())
                    .build();

            diaryDtoList.add(diaryDto);
        }

        return diaryDtoList;
    }

    @Transactional
    public Long savePost(DiaryDto diaryDto) {
        return diaryRepository.save(diaryDto.toEntity()).getId();
    }

    @Transactional
    public DiaryDto getPost(Long id) {
        Optional<DiaryEntity> diaryEntityWrapper = diaryRepository.findById(id);
        DiaryEntity diaryEntity = diaryEntityWrapper.get();

        DiaryDto diaryDto = DiaryDto.builder()
                .id(diaryEntity.getId())
                .writer(diaryEntity.getWriter())
                .title(diaryEntity.getTitle())
                .singer(diaryEntity.getSinger())
                .content(diaryEntity.getContent())
                .createdDate(diaryEntity.getCreatedDate())
                .build();

        return diaryDto;
    }

    @Transactional
    public void deletePost(Long id) {
        diaryRepository.deleteById(id);
    }

    @Transactional
    public List<DiaryDto> searchPosts(String keyword) {
        List<DiaryEntity> diaryEntities = diaryRepository.findByTitleContaining(keyword);
        List<DiaryDto> diaryDtoList = new ArrayList<>();

        if (diaryEntities.isEmpty()) return diaryDtoList;

        for (DiaryEntity diaryEntity : diaryEntities) {
            diaryDtoList.add(this.convertEntityToDto(diaryEntity));
        }

        return diaryDtoList;
    }

    private DiaryDto convertEntityToDto(DiaryEntity diaryEntity) {
        return DiaryDto.builder()
                .id(diaryEntity.getId())
                .writer(diaryEntity.getWriter())
                .title(diaryEntity.getTitle())
                .singer(diaryEntity.getSinger())
                .content(diaryEntity.getContent())
                .createdDate(diaryEntity.getCreatedDate())
                .build();
    }


}
