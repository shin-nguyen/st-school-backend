package com.stschools.api;

import com.stschools.dto.SectionDTO;
import com.stschools.service.SectionService;
import com.stschools.util.MapValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/section")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class SectionController {
    public final SectionService sectionService;
    public final MapValidationError mapValidationError;

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getSection(@PathVariable(name = "id") Long id){
        try {
            List<SectionDTO> allSectionOfCourse = sectionService.getAllSectionOfCourse(id);
            if(allSectionOfCourse == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(allSectionOfCourse);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not Found",ex);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSection(@RequestBody SectionDTO sectionDTO, BindingResult bindingResult){
        ResponseEntity<?> errorMap = mapValidationError.MapValidationError(bindingResult);
        if(errorMap != null) {
            return errorMap;
        }
        try{
            return ResponseEntity.ok().body(sectionService.save(sectionDTO));
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't save", ex);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSection(@RequestBody SectionDTO sectionDTO){
        try{
            return ResponseEntity.ok().body(sectionService.update(sectionDTO));
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't save", ex);
        }
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteSection(@PathVariable(name="id") Long id){
        try {
            Map<String, Boolean> response = new HashMap<>();
            try {
                sectionService.deleteById(id);
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
