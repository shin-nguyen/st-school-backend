package com.stschools.service;

import com.stschools.dto.SectionDTO;

import java.util.List;

public interface SectionService {
    List<SectionDTO> getAllSectionOfCourse(Long Id);
    SectionDTO save(SectionDTO sectionDTO);
    void deleteById(Long id);
    Object update(SectionDTO sectionDTO);
}
