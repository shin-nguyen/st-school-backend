package com.stschools.payload.quiz;

import com.stschools.dto.QuestionDTO;
import com.stschools.payload.user.UserResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuizRequest {
	private Long id;
	private String name;
	private String duration;
	private Boolean status;
	private Long quizRestart;
	private List<QuestionDTO> questions = new ArrayList<>();

}
