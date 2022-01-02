package com.stschools.entity;

import java.util.Date;

import javax.persistence.*;

import com.stschools.util.DateTimeControl;
import lombok.*;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "tbl_record")
@AllArgsConstructor
@NoArgsConstructor
public class Record {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "quiz_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Quiz quiz;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "user_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private User user;

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


	private Double score;

//	@Formula(value = "TIME_TO_SEC(TIMEDIFF(submit_time, start_time))/60")
//	private double time;
}
