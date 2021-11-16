package com.stschools.payload.dashboard;

import lombok.Data;

@Data
public class UserReponse {
    private String firstName;
    private String  lastName;
    private Integer order;
    private Float price;
}
