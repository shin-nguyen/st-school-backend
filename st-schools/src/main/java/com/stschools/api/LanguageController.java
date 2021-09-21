package com.stschools.api;

import com.stschools.dto.LanguageDTO;
import com.stschools.entity.Language;
import com.stschools.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/language")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        try {
            final List<LanguageDTO> list = languageService.getAll();
            if(list == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(list);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"not found");
        }
    }
}
