package com.stschools.api;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.dto.CourseDTO;
import com.stschools.dto.VideoDTO;
import com.stschools.service.VideoService;
import com.stschools.util.ObjectMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/video")
@RequiredArgsConstructor
public class VideoController {
    public final VideoService videoService;
    public final Cloudinary cloudinary;

    @GetMapping("/list")
    public ResponseEntity<?> getVideos(){
        try {
            List<VideoDTO> allVideo = videoService.getAll();
            if(allVideo == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(allVideo);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not Found",ex);
        }
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<?> getVideos(@PathVariable(name = "id") Long id){
        try {
            List<VideoDTO> allVideoOfCourse = videoService.getAllVideoOfCourse(id);
            if(allVideoOfCourse == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(allVideoOfCourse);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not Found",ex);
        }
    }



    @PostMapping("/add")
    public ResponseEntity<?> addVideo(@RequestParam String name,
                                      @RequestParam String course,
                                      @RequestParam MultipartFile file) throws IOException {

        Map uploadResult = this.cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto", "public_id", "st-school/videos/" + file.getOriginalFilename()));

        VideoDTO videoDTO = VideoDTO.builder()
                .name(name)
                .course(ObjectMapperControl.objectMapper.readValue(course, CourseDTO.class))
                .source(uploadResult.get("secure_url").toString())
                .duration((Math.round(Double.valueOf(uploadResult.get("duration").toString()) * 1) / 1))
                .build();

        try{
            return ResponseEntity.ok().body(videoService.save(videoDTO));
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't save", ex);
        }
    }

    @DeleteMapping("/{id}")
    public Long deleteVideo(@PathVariable(name="id") Long id){
        try {
            videoService.deleteById(id);
            return id;
        }  catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Found", exc);
        }
    }
}
