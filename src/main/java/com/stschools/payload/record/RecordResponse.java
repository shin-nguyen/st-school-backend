package com.stschools.payload.record;

import com.stschools.payload.user.UserResponse;
import lombok.Data;

@Data
public class RecordResponse {
	private Long id;
    private UserResponse user;
    private Double score;
    private double time;
}
