package com.stschools.service.impl;

import com.stschools.dto.VideoDTO;
import com.stschools.entity.Video;
import com.stschools.repository.CourseRepository;
import com.stschools.repository.VideoRepository;
import com.stschools.service.VideoService;
import com.stschools.util.ModelMapperControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<VideoDTO> getAllVideoOfCourse(Long id) {
        List<Video> videoList = videoRepository.findByCourse_Id(id);
        return ModelMapperControl.mapAll(videoList, VideoDTO.class);
    }

    @Override
    public List<VideoDTO> getAll() {
        List<Video> videoList = videoRepository.findAll();
        return ModelMapperControl.mapAll(videoList, VideoDTO.class);
    }

    @Override
    public VideoDTO save(VideoDTO videoDTO) {
        Video video = ModelMapperControl.map(videoDTO, Video.class);
        return ModelMapperControl.map(videoRepository.save(video), VideoDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        videoRepository.deleteById(id);
    }

    @Override
    public VideoDTO update(VideoDTO videoDTO) {
       Video video = ModelMapperControl.map(videoRepository.findById(videoDTO.getId()), Video.class);
       return ModelMapperControl.map(videoRepository.save(video), VideoDTO.class);
    }
}
