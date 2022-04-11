package com.stschools.payload.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stschools.dto.QuestionDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuizReponse {
	private Long id;
	private String name;
	@JsonIgnore
	private List<QuestionDTO> questions = new ArrayList<>();
}
