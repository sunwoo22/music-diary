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

    // 조회수 증가하기
    @Transactional
    public void increaseViews(Long id) {
        diaryRepository.increaseViews(id);
    }

    // 전체 데이터 가져오기 (공개만)
    @Transactional
    public List<DiaryDto> getDiaryList() {
        List<DiaryEntity> diaryEntities = diaryRepository.findOpenPost();
        List<DiaryDto> diaryDtoList = new ArrayList<>();

        for (DiaryEntity diaryEntity : diaryEntities) {
            diaryDtoList.add(this.convertEntityToDto(diaryEntity));
        }

        return diaryDtoList;
    }

    // 사용자 데이터 가져오기
    @Transactional
    public List<DiaryDto> getMyDiaryList(String writer) {
        List<DiaryEntity> diaryEntities = diaryRepository.findAllByWriterOrderByIdDesc(writer);
        List<DiaryDto> diaryDtoList = new ArrayList<>();

        for (DiaryEntity diaryEntity : diaryEntities) {
            diaryDtoList.add(this.convertEntityToDto(diaryEntity));
        }

        return diaryDtoList;
    }

    // 달력 데이터 가져오기
    @Transactional
    public List<DiaryDto> getPostByDate(String writer, LocalDate startDate, LocalDate endDate) {
        List<DiaryEntity> diaryEntities = diaryRepository.findByDate(writer, startDate, endDate);
        List<DiaryDto> diaryDtoList = new ArrayList<>();

        for (DiaryEntity diaryEntity : diaryEntities) {
            diaryDtoList.add(this.convertEntityToDto(diaryEntity));
        }

        return diaryDtoList;
    }

    // 음악 검색하기
    @Transactional
    public String[] setMusic(String title, String singer) throws IOException {
        String[] result = getMusicInfo(title, singer);
        if (result == null) {
            return null;
        }
        return result;
    }

    // 글 공개/비공개 바꾸기
    @Transactional
    public void updateUnopen(Long id, int unopen) {
        if (unopen == 0) {
            diaryRepository.updateUnopen(id, 1);
        } else {
            diaryRepository.updateUnopen(id, 0);
        }
    }

    // 게시글 저장하기
    @Transactional
    public Long savePost(DiaryDto diaryDto) {
        return diaryRepository.save(diaryDto.toEntity()).getId();
    }

    // 아이디로 게시글 가져오기
    @Transactional
    public DiaryDto getPost(Long id) {
        Optional<DiaryEntity> diaryEntityWrapper = diaryRepository.findById(id);
        DiaryEntity diaryEntity = diaryEntityWrapper.get();

        DiaryDto diaryDto = convertEntityToDto(diaryEntity);

//        DiaryDto diaryDto = DiaryDto.builder()
//                .id(diaryEntity.getId())
//                .writer(diaryEntity.getWriter())
//                .title(diaryEntity.getTitle())
//                .singer(diaryEntity.getSinger())
//                .imgSrc(diaryEntity.getImgSrc())
//                .mood(diaryEntity.getMood())
//                .content(diaryEntity.getContent())
//                .createdDate(diaryEntity.getCreatedDate())
//                .unopen(diaryEntity.getUnopen())
//                .views(diaryEntity.getViews())
//                .build();

        return diaryDto;
    }

    // 아이디로 게시글 삭제하기
    @Transactional
    public void deletePost(Long id) {
        diaryRepository.deleteById(id);
    }

    // 제목 keyword 검색하기
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

    // 엔티티를 디티오로 바꾸기
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
                .unopen(diaryEntity.getUnopen())
                .views(diaryEntity.getViews())
                .build();
    }


}
