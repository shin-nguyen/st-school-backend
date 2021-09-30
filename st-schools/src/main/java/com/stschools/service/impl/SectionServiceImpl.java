package com.stschools.service.impl;

import com.stschools.dto.SectionDTO;
import com.stschools.entity.Course;
import com.stschools.entity.Section;
import com.stschools.repository.CourseRepository;
import com.stschools.repository.SectionRepository;
import com.stschools.service.SectionService;
import com.stschools.util.ModelMapperControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<SectionDTO> getAllSectionOfCourse(Long id) {
        List<Section> sectionList = sectionRepository.findByCourse_Id(id);
        return ModelMapperControl.mapAll(sectionList, SectionDTO.class);
    }

    @Override
    public SectionDTO save(SectionDTO sectionDTO) {
        Section section = ModelMapperControl.map(sectionDTO, Section.class);
        return ModelMapperControl.map(sectionRepository.save(section), SectionDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        sectionRepository.deleteById(id);
    }

    @Override
    public Object update(SectionDTO sectionDTO) {
       Section section = ModelMapperControl.map(sectionRepository.findById(sectionDTO.getId()), Section.class);
       return ModelMapperControl.map(sectionRepository.save(section), SectionDTO.class);
    }
}
