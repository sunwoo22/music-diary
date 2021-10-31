package com.example.musicdiary2.service;

import com.example.musicdiary2.domain.entity.DiaryEntity;
import com.example.musicdiary2.domain.repository.DiaryRepository;
import com.example.musicdiary2.dto.DiaryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.musicdiary2.music.MusicInfo.getMusicInfo;

@Service
@AllArgsConstructor
public class DiaryService {

    private DiaryRepository diaryRepository;

    @Transactional
    public List<DiaryDto> getDiaryList() {
        List<DiaryEntity> diaryEntities = diaryRepository.findAllByOrderByIdDesc();
        List<DiaryDto> diaryDtoList = new ArrayList<>();

        for (DiaryEntity diaryEntity : diaryEntities) {
            DiaryDto diaryDto = DiaryDto.builder()
                    .id(diaryEntity.getId())
                    .writer(diaryEntity.getWriter())
                    .title(diaryEntity.getTitle())
                    .singer(diaryEntity.getSinger())
                    .imgSrc(diaryEntity.getImgSrc())
                    .mood(diaryEntity.getMood())
                    .content(diaryEntity.getContent())
                    .createdDate(diaryEntity.getCreatedDate())
                    .build();

            diaryDtoList.add(diaryDto);
        }
        return diaryDtoList;
    }

    @Transactional
    public List<DiaryDto> getMyDiaryList(String writer) {
        List<DiaryEntity> diaryEntities = diaryRepository.findAllByWriterOrderByIdDesc(writer);
        List<DiaryDto> diaryDtoList = new ArrayList<>();

        for (DiaryEntity diaryEntity : diaryEntities) {
            DiaryDto diaryDto = DiaryDto.builder()
                    .id(diaryEntity.getId())
                    .writer(diaryEntity.getWriter())
                    .title(diaryEntity.getTitle())
                    .singer(diaryEntity.getSinger())
                    .imgSrc(diaryEntity.getImgSrc())
                    .mood(diaryEntity.getMood())
                    .content(diaryEntity.getContent())
                    .createdDate(diaryEntity.getCreatedDate())
                    .build();

            diaryDtoList.add(diaryDto);
        }
        return diaryDtoList;
    }

    /*
    @Transactional
    public DiaryDto getPostByDate(String writer, LocalDateTime createdDate, LocalDateTime createdDate1) {
        DiaryEntity diaryEntity = diaryRepository.findByCreatedDate(writer, createdDate, createdDate1);

        DiaryDto diaryDto = DiaryDto.builder()
                .id(diaryEntity.getId())
                .writer(diaryEntity.getWriter())
                .title(diaryEntity.getTitle())
                .singer(diaryEntity.getSinger())
                .imgSrc(diaryEntity.getImgSrc())
                .mood(diaryEntity.getMood())
                .content(diaryEntity.getContent())
                .createdDate(diaryEntity.getCreatedDate())
                .build();

        return diaryDto;
    }
    */

    @Transactional
    public List<DiaryDto> getPostByDate(String writer, LocalDate startDate, LocalDate endDate) {
        List<DiaryEntity> diaryEntities = diaryRepository.findByDate(writer, startDate, endDate);
        List<DiaryDto> diaryDtoList = new ArrayList<>();

        for (DiaryEntity diaryEntity : diaryEntities) {
            DiaryDto diaryDto = DiaryDto.builder()
                    .id(diaryEntity.getId())
                    .writer(diaryEntity.getWriter())
                    .title(diaryEntity.getTitle())
                    .singer(diaryEntity.getSinger())
                    .imgSrc(diaryEntity.getImgSrc())
                    .mood(diaryEntity.getMood())
                    .content(diaryEntity.getContent())
                    .createdDate(diaryEntity.getCreatedDate())
                    .build();

            diaryDtoList.add(diaryDto);
        }

        return diaryDtoList;
    }

    @Transactional
    public String[] setMusic(String title, String singer) throws IOException {
        String[] result = getMusicInfo(title, singer);
        if (result == null) {
            return null;
        }
        return result;
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
                .imgSrc(diaryEntity.getImgSrc())
                .mood(diaryEntity.getMood())
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
                .imgSrc(diaryEntity.getImgSrc())
                .mood(diaryEntity.getMood())
                .content(diaryEntity.getContent())
                .createdDate(diaryEntity.getCreatedDate())
                .build();
    }


}
