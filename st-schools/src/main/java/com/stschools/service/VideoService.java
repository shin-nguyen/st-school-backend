package com.stschools.service;

import com.stschools.dto.VideoDTO;

import java.util.List;

public interface VideoService {
    List<VideoDTO> getAllVideoOfCourse(Long Id);
    VideoDTO save(VideoDTO videoDTO);
    void deleteById(Long id);
    VideoDTO update(VideoDTO videoDTO);
}
