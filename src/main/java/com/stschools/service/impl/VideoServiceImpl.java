package com.stschools.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exceptions.ApiException;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.common.enums.Role;
import com.stschools.dto.UserDTO;
import com.stschools.dto.VideoDTO;
import com.stschools.entity.Blog;
import com.stschools.entity.Order;
import com.stschools.entity.User;
import com.stschools.entity.Video;
import com.stschools.exception.ApiRequestException;
import com.stschools.payload.dashboard.DashboardResponse;
import com.stschools.payload.dashboard.GraphResponse;
import com.stschools.payload.dashboard.UserResponse;
import com.stschools.payload.user.UserFlutterReponse;
import com.stschools.payload.user.UserRequest;
import com.stschools.repository.*;
import com.stschools.service.UserService;
import com.stschools.service.VideoService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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
