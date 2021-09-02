package com.stschools.api;

import com.stschools.common.enums.FileType;
import com.stschools.dto.CourseDto;
import com.stschools.service.CourseService;
import com.stschools.util.FileControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
public class CourseController {

    public final CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getCourses(){
        try{
            final List<CourseDto> courses = courseService.getCourses();
            if(courses == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(courses);

        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found courses", ex);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CourseDto> create(@RequestParam String name,
                                            @RequestParam String description,
                                            @RequestParam Integer price
//                                            ,@RequestParam MultipartFile image
                                            ) throws IOException {
//        FileControl.saveFile(FileType.IMAGE, image);

        CourseDto course = CourseDto.builder()
                        .name(name)
                        .description(description)
                        .price(price)
//                        .image(image.getOriginalFilename())
                        .build();
        try{
            return ResponseEntity.ok().body(courseService.save(course));
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't save", ex);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<CourseDto> update(@RequestParam Long id,
                                            @RequestParam String name,
                                            @RequestParam String description,
                                            @RequestParam Integer price
//                                           ,@RequestParam MultipartFile image
                                            ) throws IOException {
//                FileControl.saveFile(FileType.IMAGE, image);

        CourseDto course = CourseDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
//              .image(image.getOriginalFilename())
                .build();
        try{
            return ResponseEntity.ok().body(courseService.update(course));
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't update", ex);
        }
    }


    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable (name = "id") Long id){
        try {
            Map<String, Boolean> response = new HashMap<>();
            try {
                courseService.deleteById(id);
                response.put("deleted", Boolean.TRUE);

            } catch (Exception exception) {
                response.put("deleted", Boolean.FALSE);
            }
            return response;
        }  catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Found", exc);
        }

    }
}
