package com.stschools.service;

import com.stschools.dto.LectureDTO;
import com.stschools.dto.SectionDTO;
import com.stschools.entity.Lecture;

import java.util.List;

public interface LectureService {
    List<LectureDTO> getAllLectureOfSection(Long id);

    LectureDTO save(LectureDTO lectureDTO);

    LectureDTO update(LectureDTO lectureDTO);

    void deleteById(Long id);
}
