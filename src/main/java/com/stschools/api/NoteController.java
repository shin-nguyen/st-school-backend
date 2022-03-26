package com.stschools.api;

import com.stschools.dto.NoteDTO;
import com.stschools.dto.UserDTO;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.NoteService;
import com.stschools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getNotes(@PathVariable(name = "id") Long courseId, @CurrentUser UserPrincipal user){
        try{
            UserDTO userDTO = userService.findUserById(user.getId());
            return new ResponseEntity<>(noteService.getNotes(courseId, userDTO.getId()), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> addNote(@RequestBody NoteDTO noteDTO, @CurrentUser UserPrincipal user){
        try{
            UserDTO userDTO = userService.findUserById(user.getId());
            noteDTO.setUser(userDTO);
            return new ResponseEntity<>(noteService.addNote(noteDTO), HttpStatus.CREATED);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
