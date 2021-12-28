package com.stschools.payload.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String firstName;
    private String  lastName;
    private long order;
    private long price;
}
