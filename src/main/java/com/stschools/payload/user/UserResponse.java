package com.stschools.payload.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stschools.common.enums.AuthProvider;
import com.stschools.common.enums.Role;
import com.stschools.util.DateTimeControl;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
}
