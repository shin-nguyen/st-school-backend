package com.stschools.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String phone;
    private Integer balance;
    private Boolean status;
}
