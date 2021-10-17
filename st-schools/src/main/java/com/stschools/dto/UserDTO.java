package com.stschools.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stschools.common.enums.AuthProvider;
import com.stschools.common.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private Long id;
    @Email(message = "needs to be an email")
    @NotBlank(message = "email is required")
    private String email;

    private String password;
    private String firstName;
    private String lastName;

    private String avatar;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthday;

    private String address;
    private String phone;
    private boolean active;

    private String activationCode;
    private String passwordResetCode;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdTime;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updateTime;


    private Set<Role> roles = new HashSet<>();
}
