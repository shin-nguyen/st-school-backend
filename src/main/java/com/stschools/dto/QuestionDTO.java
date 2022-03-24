package com.stschools.dto;

import java.util.ArrayList;
import java.util.List;

import com.stschools.entity.Answer;
import com.stschools.entity.Quiz;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
public class QuestionDTO {
	private Long id;
	private String description;
	private String correct;
	private List<Answer> options = new ArrayList<>();
}
