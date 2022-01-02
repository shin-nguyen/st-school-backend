package com.stschools.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.stschools.util.DateTimeControl;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tbl_answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 150, nullable = false)
	private String text;

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
}
