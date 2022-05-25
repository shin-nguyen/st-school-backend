package com.stschools.payload.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String about;
    private MultipartFile avatar;
}
