package com.stschools.api;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.dto.CourseDTO;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.CourseService;
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
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class CourseController {

    public final CourseService courseService;
    public final Cloudinary cloudinary;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable (name = "id") Long id){
        try{
            final CourseDTO courseDto = courseService.findByID(id);
            if(courseDto == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(courseDto);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/list")
    public ResponseEntity<List<?>> getCoursesByAdmin(){
        try{
            final List<CourseDTO> courses = courseService.getCoursesByAdmin();
            if(courses == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(courses);

        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found courses", ex);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<?>> getCourses(@CurrentUser UserPrincipal user){
        try{
            final List<CourseDTO> courses = courseService.getCourses(user.getId());
            if(courses == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(courses);

        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found courses", ex);
        }
    }

    @GetMapping("/list/purchased")
    public ResponseEntity<List<?>> getCoursesByUserID(@CurrentUser UserPrincipal user){
        try{
            final List<CourseDTO> courses = courseService.findByUserId(user.getId());
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
    public ResponseEntity<CourseDTO> create(@RequestParam String name,
                                            @RequestParam String description,
                                            @RequestParam String language,
                                            @RequestParam Integer price,
                                            @RequestParam MultipartFile file
                                            ) throws IOException {
        Map uploadResult = this.cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto", "public_id", "st-school/images/" + file.getOriginalFilename()));

        CourseDTO course = CourseDTO.builder()
                        .name(name)
                        .description(description)
                        .language(language)
                        .price(price)
                        .image(uploadResult.get("secure_url").toString())
                        .build();
        try{
            return ResponseEntity.ok().body(courseService.save(course));
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't save", ex);
        }
    }
    
    @PutMapping("/update")
    public ResponseEntity<CourseDTO> update(@RequestParam Long id,
                                            @RequestParam String name,
                                            @RequestParam String description,
                                            @RequestParam String language,
                                            @RequestParam Integer price,
                                            @RequestParam MultipartFile file
                                            ) throws IOException {
        Map uploadResult = this.cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto", "public_id", "st-school/images/" + file.getOriginalFilename()));

        CourseDTO course = CourseDTO.builder()
                .id(id)
                .name(name)
                .description(description)
                .language(language)
                .price(price)
                .image(uploadResult.get("secure_url").toString())
                .build();
        try{
            return ResponseEntity.ok().body(courseService.update(course));
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't update", ex);
        }
    }


    @DeleteMapping("/{id}")
    public Long delete(@PathVariable (name = "id") Long id){
        try {
            courseService.deleteById(id);
            return id;
        }  catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't delete", exc);
        }
    }
}
