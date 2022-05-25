package com.stschools.payload.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFlutterReponse{
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String about;
    private String avatar;
}
