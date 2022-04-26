package com.stschools.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.stschools.entity.Course;
import com.stschools.entity.Question;
import com.stschools.entity.User;
import com.stschools.payload.user.UserResponse;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import javax.persistence.*;

@Data
public class QuizDTO {
	private Long id;
	private String name;
	private String code;
	private UserResponse createBy;
	private String duration;
	private Boolean status;

	@Override
	public String toString() {
		return questions.toString();
	}
	private Long quizRestart;
	private List<QuestionDTO> questions = new ArrayList<>();
	private CourseDTO course;
}
