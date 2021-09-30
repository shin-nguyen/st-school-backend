package com.stschools.service.impl;

import com.stschools.dto.LectureDTO;
import com.stschools.entity.Lecture;
import com.stschools.repository.LectureRepository;
import com.stschools.service.LectureService;
import com.stschools.util.ModelMapperControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {
    @Autowired
    LectureRepository lectureRepository;

    @Override
    public List<LectureDTO> getAllLectureOfSection(Long id) {
        return ModelMapperControl.mapAll(lectureRepository.findBySection_Id(id), LectureDTO.class);
    }

    @Override
    public LectureDTO save(LectureDTO lectureDTO) {
        Lecture lecture = ModelMapperControl.map(lectureDTO, Lecture.class);
        return ModelMapperControl.map(lectureRepository.save(lecture), LectureDTO.class);
    }

    @Override
    public LectureDTO update(LectureDTO lectureDTO) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        lectureRepository.deleteById(id);
    }
}
