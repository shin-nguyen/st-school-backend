package com.stschools.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.stschools.util.DateTimeControl;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "tbl_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 150, nullable = false)
	private String description;
	@Column
	private String image;


	@OneToMany
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Answer> options = new ArrayList<>();

	@Column
	private Long correctAnswer;

	@Column
	private String createdTime;

	@Column
	private String updateTime;

	@PrePersist
	protected void onCreate() {
		this.createdTime = DateTimeControl.formatDate(new Date());
		this.updateTime = DateTimeControl.formatDate(new Date());
	}

	@PreUpdate
	protected void onUpdate() {
		this.updateTime =  DateTimeControl.formatDate(new Date());
	}
//	correctAnswer: {
//		type: String,
//				required: true,
//	},
//	to
//	Answer.correct
}
