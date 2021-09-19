package com.stschools.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stschools.common.enums.AuthenticationType;
import com.stschools.entity.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    @Email(message = "needs to be an email")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "username is required")
    private String username;
    private String password;
    private String fullName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;
    private String address;
    private String phone;
    private String avatar;
}
