package com.stschools.payload.record;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.stschools.dto.UserDTO;
import lombok.Data;

@Data
public class RecordResponse {
	private Long id;
    private UserDTO user;
    private Double score;
    private double time;
}
