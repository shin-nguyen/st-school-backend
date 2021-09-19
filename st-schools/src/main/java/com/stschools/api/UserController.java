package com.stschools.api;

import com.stschools.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService IUserService;

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getUsers(){
//        return ResponseEntity.ok().body(userService.getUsers());
//    }

//    @GetMapping("/customers")
//    public ResponseEntity<List<UserDto>> getCustomers(){
//        try{
//            final List<UserDto> customers = userService.getCustomers();
//            if(customers == null){
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            return ResponseEntity.ok().body(customers);
//
//        }
//        catch (Exception ex){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Found Customers", ex);
//        }
//    }
}
