package com.stschools.api;

import com.stschools.dto.UserDto;
import com.stschools.entity.User;
import com.stschools.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
public class UserController {

    private final UserService userService;

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getUsers(){
//        return ResponseEntity.ok().body(userService.getUsers());
//    }

    @GetMapping("/customers")
    public ResponseEntity<List<UserDto>> getCustomers(){
        try{
            final List<UserDto> customers = userService.getCustomers();
            if(customers == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(customers);

        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Found Customers", ex);
        }
    }
}
