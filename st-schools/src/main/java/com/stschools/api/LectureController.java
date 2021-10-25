package com.stschools.api;

import com.stschools.dto.LectureDTO;
import com.stschools.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/lecture")
public class LectureController {
    @Autowired
    LectureService lectureService;

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getLecture(@PathVariable(name = "id") Long id){
        try {
            List<LectureDTO> allLectureOfSection = lectureService.getAllLectureOfSection(id);
            if(allLectureOfSection == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(allLectureOfSection);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not Found",ex);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLecture(@RequestBody LectureDTO lectureDTO){
        try{
            return ResponseEntity.ok().body(lectureService.save(lectureDTO));
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't save", ex);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLecture(@RequestBody LectureDTO lectureDTO){
        try{
            return ResponseEntity.ok().body(lectureService.update(lectureDTO));
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't save", ex);
        }
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteLecture(@PathVariable(name="id") Long id){
        try {
            Map<String, Boolean> response = new HashMap<>();
            try {
                lectureService.deleteById(id);
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
